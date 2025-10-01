package com.jiksoft.library.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jiksoft.library.bo.LoanBO;
import com.jiksoft.library.controllers.docs.LoanControllerDocs;
import com.jiksoft.library.dto.ApiResponseDTO;
import com.jiksoft.library.dto.responses.ResponseLoanDTO;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.LoanException;
import com.jiksoft.library.mappers.LoanMapper;
import com.jiksoft.library.services.LoanService;

@RestController
@Slf4j
@RequestMapping(value = "/v1/loans")
@AllArgsConstructor
public class LoanController implements LoanControllerDocs{

    private final LoanService loanService;
    private final LoanMapper loanMapper;

    @Override
    @PostMapping(value = "/borrow", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponseDTO<ResponseLoanDTO>> borrowBook(
            @RequestParam String userId,
            @RequestParam String bookId) throws LoanException {

        LoanBO loanBo = loanService.borrowBook(userId, bookId);
        ResponseLoanDTO loanDto = loanMapper.fromBoToResponseDto(loanBo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.<ResponseLoanDTO>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Book borrowed successfully.")
                        .body(loanDto)
                        .build());
    }

    @Override
    @PostMapping(value = "/return", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<ResponseLoanDTO>> returnBook(
            @RequestParam String userId,
            @RequestParam String bookId) throws LoanException {

        LoanBO loanBo = loanService.returnBook(userId, bookId);
        ResponseLoanDTO loanDto = loanMapper.fromBoToResponseDto(loanBo);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<ResponseLoanDTO>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Book returned successfully.")
                        .body(loanDto)
                        .build());
    }

    @Override
    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<List<ResponseLoanDTO>>> listUserLoans(@PathVariable String userId) {

        List<LoanBO> loansBo = loanService.listUserLoans(userId);
        List<ResponseLoanDTO> loansDto = loanMapper.fromListBoToListResponseDto(loansBo);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<List<ResponseLoanDTO>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("User loans retrieved successfully.")
                        .body(loansDto)
                        .build());
    }
}