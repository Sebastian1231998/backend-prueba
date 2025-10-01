package com.jiksoft.library.controllers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jiksoft.library.bo.LoanBO;
import com.jiksoft.library.dto.responses.ResponseLoanDTO;
import com.jiksoft.library.mappers.LoanMapper;
import com.jiksoft.library.services.LoanService;
import com.jiksoft.library.utils.LoanTestsUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {LoanController.class})
@ActiveProfiles("development")
@WebMvcTest(controllers = LoanController.class)
class LoanControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoanService loanService;

    @MockitoBean
    private LoanMapper loanMapper;

    @Test
    void test_BorrowBook_Should_Return_Object_And_Code201_When_Exists() throws Exception {

        LoanBO responseBo = LoanTestsUtil.getLoanBo();
        ResponseLoanDTO responseDto = LoanTestsUtil.getResponseLoanDTO();

        Mockito.when(loanService.borrowBook(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
               .thenReturn(responseBo);
        Mockito.when(loanMapper.fromBoToResponseDto(ArgumentMatchers.any(LoanBO.class)))
               .thenReturn(responseDto);

        mockMvc.perform(post("/v1/loans/borrow")
                .param("userId", responseBo.getUserId())
                .param("bookId", responseBo.getBook().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.status_code").value(201))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.body").isNotEmpty());

        verify(loanService, times(1)).borrowBook(anyString(), anyString());
        verify(loanMapper, times(1)).fromBoToResponseDto(any(LoanBO.class));
    }

    @Test
    void test_ReturnBook_Should_Return_Object_And_Code200_When_Exists() throws Exception {

        LoanBO responseBo = LoanTestsUtil.getLoanBo();
        ResponseLoanDTO responseDto = LoanTestsUtil.getResponseLoanDTO();

        Mockito.when(loanService.returnBook(anyString(), anyString())).thenReturn(responseBo);
        Mockito.when(loanMapper.fromBoToResponseDto(any(LoanBO.class))).thenReturn(responseDto);

        mockMvc.perform(post("/v1/loans/return")
                .param("userId", responseBo.getUserId())
                .param("bookId", responseBo.getBook().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status_code").value(200))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.body").isNotEmpty());

        verify(loanService, times(1)).returnBook(anyString(), anyString());
        verify(loanMapper, times(1)).fromBoToResponseDto(any(LoanBO.class));
    }

    @Test
    void test_ListUserLoans_Should_Return_Object_And_Code200_When_Invoked() throws Exception {

        LoanBO responseBo = LoanTestsUtil.getLoanBo();
        List<LoanBO> listBo = LoanTestsUtil.getLoanBoList();
        List<ResponseLoanDTO> listDto = LoanTestsUtil.getResponseLoanDTOList();

        when(loanService.listUserLoans(anyString())).thenReturn(listBo);
        when(loanMapper.fromListBoToListResponseDto(listBo)).thenReturn(listDto);

        mockMvc.perform(get("/v1/loans/user/{userId}", responseBo.getUserId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status_code").value(200))
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.body").isNotEmpty());

        verify(loanService, times(1)).listUserLoans(anyString());
        verify(loanMapper, times(1)).fromListBoToListResponseDto(anyList());
    }
}