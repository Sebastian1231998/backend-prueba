package com.jiksoft.library.services.impl;

import java.util.List;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jiksoft.library.annotations.BasicLog;
import com.jiksoft.library.bo.BookBO;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions;
import com.jiksoft.library.mappers.BookMapper;
import com.jiksoft.library.models.library.BookEntity;
import com.jiksoft.library.models.library.LibraryEntity;
import com.jiksoft.library.repositories.jpa.BookRepository;
import com.jiksoft.library.repositories.jpa.LibraryRepository;
import com.jiksoft.library.services.BookService;
import com.jiksoft.library.services.LibraryService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional(rollbackOn = Exception.class)
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper mapper;
    private final LibraryService libraryService;
    private final LibraryRepository libraryRepository;
    

    @BasicLog
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public BookBO findById(String id) {
        BookEntity entity = getById(id);
        return mapper.fromEntityToBo(entity);
    }

 
    @Override
    @BasicLog
    @Transactional(Transactional.TxType.REQUIRED)
    public List<BookBO> listByLibraryId(String libraryId) {
 
        libraryService.findById(libraryId);
        List<BookEntity> entityList = bookRepository.findDeletedBooksByLibrary(UUID.fromString(libraryId));
        return mapper.fromEntityListToBoList(entityList);
    }


    @Override
    @BasicLog
    public BookBO create(BookBO createBo) throws BusinessRulesListExceptions.LibraryManagementServiceException {

        LibraryEntity library = libraryRepository.findById(UUID.fromString(createBo.getLibrary().getId()))
            .orElseThrow(() -> new EntityNotFoundException("Library with id " + createBo.getLibrary().getId() + " not found."));
        
        BookEntity entityToSave = mapper.fromBoToEntity(createBo);
        entityToSave.setLibrary(library);
        BookEntity entitySaved = bookRepository.save(entityToSave);
        return mapper.fromEntityToBo(entitySaved);
    }

    @Override
    @BasicLog
    public BookBO update(String id, BookBO updateBo) throws BusinessRulesListExceptions.LibraryManagementServiceException {

        BookEntity entity = getById(id);
        if (updateBo.getLibrary() != null && 
            !updateBo.getLibrary().getId().equals(entity.getLibrary().getId().toString())) {

            UUID newLibraryId = UUID.fromString(updateBo.getLibrary().getId());
            LibraryEntity newLibrary = libraryRepository.findById(newLibraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library with id " + newLibraryId + " does not exist."));
            entity.setLibrary(newLibrary);
        }

        mapper.fromBoToEntity(updateBo, entity);

        bookRepository.save(entity);

        return mapper.fromEntityToBo(entity);
    }

    @Override
    @BasicLog
    public void delete(String id) {
        UUID uuid = UUID.fromString(id);
        getById(id);
        bookRepository.deleteById(uuid);
    }

    private BookEntity getById(String id) {
        UUID uuid = UUID.fromString(id);
        return bookRepository.findById(uuid)
            .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found."));
    }

}
