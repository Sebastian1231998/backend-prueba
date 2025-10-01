package com.jiksoft.library.services;

import java.util.List;

import com.jiksoft.library.bo.BookBO;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.LibraryManagementServiceException;


public interface BookService {
    BookBO findById(String id);

    List<BookBO> listByLibraryId(String libraryId) throws LibraryManagementServiceException;

    BookBO create(BookBO createBo) throws LibraryManagementServiceException;

    BookBO update(String id, BookBO updateBo) throws LibraryManagementServiceException;

    void delete(String id);
}
