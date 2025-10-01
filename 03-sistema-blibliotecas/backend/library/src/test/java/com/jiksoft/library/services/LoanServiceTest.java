package com.jiksoft.library.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jiksoft.library.bo.LoanBO;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.LoanException;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.MembershipException;
import com.jiksoft.library.mappers.LoanMapper;
import com.jiksoft.library.models.library.BookEntity;
import com.jiksoft.library.models.library.LoanEntity;
import com.jiksoft.library.repositories.jpa.BookRepository;
import com.jiksoft.library.repositories.jpa.LoanRepository;
import com.jiksoft.library.services.impl.LoanServiceImpl;
import com.jiksoft.library.utils.LoanTestsUtil;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @InjectMocks
    private LoanServiceImpl service;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private MembershipService membershipService;

    @Mock
    private LoanMapper mapper;

    // ---------- CASO 1: Libro no encontrado ----------
    @Test
    void borrowBook_ShouldThrow_WhenBookNotFound() {
        UUID bookId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        LoanException exception = assertThrows(LoanException.class,
                () -> service.borrowBook(userId.toString(), bookId.toString()));

        assertEquals("Book not found", exception.getMessage());
        verify(bookRepository, times(1)).findById(bookId);
        verifyNoInteractions(membershipService, loanRepository, mapper);
    }

    // ---------- CASO 2: Usuario sin membresía ----------
    @Test
    void borrowBook_ShouldThrow_WhenUserNoMembership() throws MembershipException {
        BookEntity book = LoanTestsUtil.getBookEntity();
        UUID bookId = book.getId();
        UUID userId = UUID.randomUUID();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        doThrow(new MembershipException("No membership"))
                .when(membershipService)
                .validateUserMembership(anyString(), anyString());

        LoanException exception = assertThrows(LoanException.class,
                () -> service.borrowBook(userId.toString(), bookId.toString()));

        assertEquals("User does not have an active membership in this library", exception.getMessage());
        verify(bookRepository, times(1)).findById(bookId);
        verify(membershipService, times(1)).validateUserMembership(userId.toString(), book.getLibrary().getId().toString());
        verifyNoInteractions(loanRepository, mapper);
    }

    // ---------- CASO 3: Libro ya prestado ----------
    @Test
    void borrowBook_ShouldThrow_WhenBookAlreadyLoaned() throws MembershipException {
        BookEntity book = LoanTestsUtil.getBookEntity();
        UUID bookId = book.getId();
        UUID userId = UUID.randomUUID();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        doNothing().when(membershipService).validateUserMembership(anyString(), anyString());
        when(loanRepository.findByBookAndReturnDateIsNull(book))
                .thenReturn(Optional.of(LoanTestsUtil.getLoanEntity()));

        LoanException exception = assertThrows(LoanException.class,
                () -> service.borrowBook(userId.toString(), bookId.toString()));

        assertEquals("Book is currently loaned by another user", exception.getMessage());
        verify(bookRepository, times(1)).findById(bookId);
        verify(membershipService, times(1)).validateUserMembership(userId.toString(), book.getLibrary().getId().toString());
        verify(loanRepository, times(1)).findByBookAndReturnDateIsNull(book);
        verifyNoInteractions(mapper);
    }

    // ---------- CASO 4: Éxito ----------
    @Test
    void borrowBook_ShouldReturnLoanBO_WhenSuccess() throws MembershipException, LoanException {
        BookEntity book = LoanTestsUtil.getBookEntity();
        LoanEntity savedLoan = LoanTestsUtil.getLoanEntity();
        LoanBO loanBo = LoanTestsUtil.getLoanBo();

        UUID bookId = book.getId();
        UUID userId = UUID.randomUUID();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        doNothing().when(membershipService).validateUserMembership(anyString(), anyString());
        when(loanRepository.findByBookAndReturnDateIsNull(book)).thenReturn(Optional.empty());
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(savedLoan);
        when(mapper.fromEntityToBo(savedLoan)).thenReturn(loanBo);

        LoanBO result = service.borrowBook(userId.toString(), bookId.toString());

        assertNotNull(result);
        assertEquals(loanBo, result);

        verify(bookRepository, times(1)).findById(bookId);
        verify(membershipService, times(1)).validateUserMembership(userId.toString(), book.getLibrary().getId().toString());
        verify(loanRepository, times(1)).findByBookAndReturnDateIsNull(book);
        verify(loanRepository, times(1)).save(any(LoanEntity.class));
        verify(mapper, times(1)).fromEntityToBo(savedLoan);
    }


    // ---------- CASO 1: Libro no encontrado ----------
    @Test
    void returnBook_ShouldThrow_WhenBookNotFound() {
        UUID bookId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        LoanException exception = assertThrows(LoanException.class,
                () -> service.returnBook(userId.toString(), bookId.toString()));

        assertEquals("Book not found", exception.getMessage());

        verify(bookRepository, times(1)).findById(bookId);
        verifyNoInteractions(loanRepository, mapper);
    }

    // ---------- CASO 2: Préstamo no encontrado o ya devuelto ----------
    @Test
    void returnBook_ShouldThrow_WhenLoanNotFound() {
        BookEntity book = LoanTestsUtil.getBookEntity();
        UUID bookId = book.getId();
        UUID userId = UUID.randomUUID();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(loanRepository.findByUserIdAndBookAndReturnDateIsNull(any(), any()))
                .thenReturn(Optional.empty());

        LoanException exception = assertThrows(LoanException.class,
                () -> service.returnBook(userId.toString(), bookId.toString()));

        assertEquals("Loan not found or already returned", exception.getMessage());

        verify(bookRepository, times(1)).findById(bookId);
        verify(loanRepository, times(1)).findByUserIdAndBookAndReturnDateIsNull(UUID.fromString(userId.toString()), book);
        verifyNoInteractions(mapper);
    }

    // ---------- CASO 3: Éxito ----------
    @Test
    void returnBook_ShouldReturnLoanBO_WhenSuccess() throws LoanException {
        BookEntity book = LoanTestsUtil.getBookEntity();
        LoanEntity loan = LoanTestsUtil.getLoanEntity();
        LoanEntity savedLoan = LoanTestsUtil.getLoanEntity();
        LoanBO loanBo = LoanTestsUtil.getLoanBo();

        UUID bookId = book.getId();
        UUID userId = UUID.randomUUID();

        // mocks
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(loanRepository.findByUserIdAndBookAndReturnDateIsNull(any(UUID.class), any(BookEntity.class)))
                .thenReturn(Optional.of(loan));
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(savedLoan);
        when(mapper.fromEntityToBo(savedLoan)).thenReturn(loanBo);

        LoanBO result = service.returnBook(userId.toString(), bookId.toString());

        assertNotNull(result);
        assertEquals(loanBo, result);

        verify(bookRepository, times(1)).findById(bookId);
        verify(loanRepository, times(1)).findByUserIdAndBookAndReturnDateIsNull(UUID.fromString(userId.toString()), book);
        verify(loanRepository, times(1)).save(loan);
        verify(mapper, times(1)).fromEntityToBo(savedLoan);
    }


     // ---------- CASO 1: Usuario sin préstamos activos ----------
    @Test
    void listUserLoans_ShouldReturnEmptyList_WhenNoActiveLoans() {
        UUID userId = UUID.randomUUID();

        when(loanRepository.findByUserIdAndReturnDateIsNull(userId))
                .thenReturn(Collections.emptyList());

        List<LoanBO> result = service.listUserLoans(userId.toString());

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(loanRepository, times(1)).findByUserIdAndReturnDateIsNull(userId);
        verifyNoInteractions(mapper);
    }

    // ---------- CASO 2: Usuario con préstamos activos ----------
    @Test
    void listUserLoans_ShouldReturnLoanBOList_WhenUserHasActiveLoans() {
        UUID userId = UUID.randomUUID();
        LoanEntity loan1 = LoanTestsUtil.getLoanEntity();
        LoanEntity loan2 = LoanTestsUtil.getLoanEntity();
        List<LoanEntity> loans = Arrays.asList(loan1, loan2);

        LoanBO bo1 = LoanTestsUtil.getLoanBo();
        LoanBO bo2 = LoanTestsUtil.getLoanBo();

        when(loanRepository.findByUserIdAndReturnDateIsNull(userId)).thenReturn(loans);
        when(mapper.fromEntityToBo(loan1)).thenReturn(bo1);
        when(mapper.fromEntityToBo(loan2)).thenReturn(bo2);

        List<LoanBO> result = service.listUserLoans(userId.toString());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(Arrays.asList(bo1, bo2), result);

        verify(loanRepository, times(1)).findByUserIdAndReturnDateIsNull(userId);
        verify(mapper, times(1)).fromEntityToBo(loan1);
        verify(mapper, times(1)).fromEntityToBo(loan2);
    }

    
}
