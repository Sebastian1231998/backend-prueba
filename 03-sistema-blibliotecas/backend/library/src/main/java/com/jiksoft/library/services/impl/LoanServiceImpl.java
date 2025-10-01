package com.jiksoft.library.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiksoft.library.bo.LoanBO;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.LoanException;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.MembershipException;
import com.jiksoft.library.mappers.LoanMapper;
import com.jiksoft.library.models.library.BookEntity;
import com.jiksoft.library.models.library.LoanEntity;
import com.jiksoft.library.repositories.jpa.BookRepository;
import com.jiksoft.library.repositories.jpa.LoanRepository;
import com.jiksoft.library.services.LoanService;
import com.jiksoft.library.services.MembershipService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final LoanMapper mapper;
    private final MembershipService membershipService;
    @Override
    public LoanBO borrowBook(String userId, String bookId) throws LoanException {

        UUID userUuid = UUID.fromString(userId);
        UUID bookUuid = UUID.fromString(bookId);

        BookEntity book = bookRepository.findById(bookUuid)
                .orElseThrow(() -> new LoanException("Book not found"));

        try {
            membershipService.validateUserMembership(userId, book.getLibrary().getId().toString());
        } catch (MembershipException e) {
            throw new LoanException("User does not have an active membership in this library");
        }

        if (loanRepository.findByBookAndReturnDateIsNull(book).isPresent()) {
            throw new LoanException("Book is currently loaned by another user");
        }

        LoanEntity loan = LoanEntity.builder()
                .book(book)
                .userId(userUuid)
                .loanDate(LocalDate.now())
                .returnDate(null)
                .build();

        LoanEntity saved = loanRepository.save(loan);
        return mapper.fromEntityToBo(saved);
    }

    @Override
    public LoanBO returnBook(String userId, String bookId) throws LoanException {

        UUID userUuid = UUID.fromString(userId);
        UUID bookUuid = UUID.fromString(bookId);

        BookEntity book = bookRepository.findById(bookUuid)
                .orElseThrow(() -> new LoanException("Book not found"));

        LoanEntity loan = loanRepository.findByUserIdAndBookAndReturnDateIsNull(userUuid, book)
                .orElseThrow(() -> new LoanException("Loan not found or already returned"));

        loan.setReturnDate(LocalDate.now());
        LoanEntity saved = loanRepository.save(loan);

        return mapper.fromEntityToBo(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanBO> listUserLoans(String userId) {
        UUID userUuid = UUID.fromString(userId);
        List<LoanEntity> loans = loanRepository.findByUserIdAndReturnDateIsNull(userUuid);
        return loans.stream()
                .map(mapper::fromEntityToBo)
                .collect(Collectors.toList());
    }
}