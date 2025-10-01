package com.jiksoft.library.services;

import java.util.List;

import com.jiksoft.library.bo.LibraryBO;


public interface LibraryService {

    LibraryBO findById(String id);

    List<LibraryBO> findAll();

    LibraryBO create(LibraryBO libraryBo);

    LibraryBO update(String id, LibraryBO libraryBo);

    void delete(String id);
}
