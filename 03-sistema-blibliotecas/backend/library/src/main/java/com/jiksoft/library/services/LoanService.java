package com.jiksoft.library.services;

import java.util.List;

import com.jiksoft.library.bo.LoanBO;

import com.jiksoft.library.exceptions.BusinessRulesListExceptions.LoanException;

public interface LoanService {

    public LoanBO borrowBook(String userId, String bookId) throws LoanException;

    LoanBO returnBook(String userId, String bookId) throws LoanException;

    List<LoanBO> listUserLoans(String userId);
}