package com.jiksoft.library.utils;

import com.jiksoft.library.bo.BookBO;
import com.jiksoft.library.bo.LoanBO;
import com.jiksoft.library.bo.LibraryBO;
import com.jiksoft.library.bo.MembershipBO;
import com.jiksoft.library.dto.responses.ResponseBookDTO;
import com.jiksoft.library.dto.responses.ResponseLoanDTO;
import com.jiksoft.library.models.library.BookEntity;
import com.jiksoft.library.models.library.LoanEntity;
import com.jiksoft.library.models.library.LibraryEntity;
import com.jiksoft.library.models.library.MembershipEntity;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class LoanTestsUtil {

    public static BookBO getBookBo() {
        return BookBO.builder()
                .id(UUID.randomUUID().toString())
                .name("Effective Java")
                .author("Joshua Bloch")
                .build();
    }

    public static BookEntity getBookEntity() {
        return BookEntity.builder()
                .id(UUID.randomUUID())
                .name("Effective Java")
                .author("Joshua Bloch")
                .library(getLibraryEntity())
                .build();
    }

    public static List<BookEntity> getBookEntityList() {
        return Collections.singletonList(getBookEntity());
    }

    public static LoanBO getLoanBo() {
        return LoanBO.builder()
                .id(UUID.randomUUID().toString())
                .book(getBookBo())
                .userId(UUID.randomUUID().toString())
                .loanDate(LocalDate.now())
                .returnDate(null)
                .build();
    }

    public static List<LoanBO> getLoanBoList() {
        return Collections.singletonList(getLoanBo());
    }

    public static LoanEntity getLoanEntity() {
        return LoanEntity.builder()
                .id(UUID.randomUUID())
                .book(getBookEntity())
                .userId(UUID.randomUUID())
                .loanDate(LocalDate.now())
                .returnDate(null)
                .build();
    }

    public static List<LoanEntity> getLoanEntityList() {
        return Collections.singletonList(getLoanEntity());
    }

    public static LibraryBO getLibraryBo() {
        return LibraryBO.builder()
                .id(UUID.randomUUID().toString())
                .name("Central Library")
                .build();
    }

    public static LibraryEntity getLibraryEntity() {
        return LibraryEntity.builder()
                .id(UUID.randomUUID())
                .name("Central Library")
                .build();
    }

    public static MembershipBO getMembershipBo() {
        return MembershipBO.builder()
                .id(UUID.randomUUID().toString())
                .userId(UUID.randomUUID().toString())
                .library(getLibraryBo())
                .type("STANDARD")
                .startDate(LocalDate.now().minusMonths(1))
                .endDate(null)
                .build();
    }

    public static MembershipEntity getMembershipEntity() {
        return MembershipEntity.builder()
                .id(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .library(getLibraryEntity())
                .type("STANDARD")
                .startDate(LocalDate.now().minusMonths(1))
                .endDate(null)
                .build();
    }

    public static List<MembershipEntity> getMembershipEntityList() {
        return Collections.singletonList(getMembershipEntity());
    }

    public static ResponseBookDTO getResponseBookDTO() {
        return ResponseBookDTO.builder()
                .id(UUID.randomUUID().toString())
                .name("Effective Java")
                .author("Joshua Bloch")
                .build();
    }

    public static List<ResponseBookDTO> getResponseBookDTOList() {
        return Collections.singletonList(getResponseBookDTO());
    }

    public static ResponseLoanDTO getResponseLoanDTO() {
        return ResponseLoanDTO.builder()
                .id(UUID.randomUUID().toString())
                .userId(UUID.randomUUID().toString())
                .book(getResponseBookDTO())
                .loanDate(LocalDate.now())
                .returnDate(null)
                .build();
    }

    public static List<ResponseLoanDTO> getResponseLoanDTOList() {
        return Collections.singletonList(getResponseLoanDTO());
    }
}