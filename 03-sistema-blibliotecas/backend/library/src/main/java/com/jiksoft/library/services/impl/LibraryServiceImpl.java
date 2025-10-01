package com.jiksoft.library.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiksoft.library.bo.LibraryBO;
import com.jiksoft.library.mappers.LibraryMapper;
import com.jiksoft.library.models.library.LibraryEntity;
import com.jiksoft.library.repositories.jpa.LibraryRepository;
import com.jiksoft.library.services.LibraryService;

import jakarta.persistence.EntityNotFoundException;

import lombok.AllArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final LibraryMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public LibraryBO findById(String id) {
        LibraryEntity entity = getById(id);
        return mapper.fromEntityToBo(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LibraryBO> findAll() {
        List<LibraryEntity> entityList = libraryRepository.findAll();
        return mapper.fromEntityListToBoList(entityList);
    }

    @Override
    public LibraryBO create(LibraryBO libraryBo) {
        LibraryEntity entityToSave = mapper.fromBoToEntity(libraryBo);
        LibraryEntity saved = libraryRepository.save(entityToSave);
        return mapper.fromEntityToBo(saved);
    }

    @Override
    public LibraryBO update(String id, LibraryBO libraryBo) {
        LibraryEntity entity = getById(id);
        mapper.fromBoToEntity(libraryBo, entity);
        libraryRepository.save(entity);
        return mapper.fromEntityToBo(entity);
    }

    @Override
    public void delete(String id) {
        LibraryEntity entity = getById(id);
        libraryRepository.delete(entity);
    }

    private LibraryEntity getById(String id) {
        UUID uuid = UUID.fromString(id);
        return libraryRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Library with id " + id + " not found."));
    }
}