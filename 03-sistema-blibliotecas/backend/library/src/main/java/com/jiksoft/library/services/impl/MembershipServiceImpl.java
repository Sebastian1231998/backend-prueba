package com.jiksoft.library.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiksoft.library.bo.MembershipBO;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.MembershipException;
import com.jiksoft.library.mappers.MembershipMapper;
import com.jiksoft.library.models.library.LibraryEntity;
import com.jiksoft.library.models.library.MembershipEntity;
import com.jiksoft.library.repositories.jpa.LibraryRepository;
import com.jiksoft.library.repositories.jpa.MembershipRepository;
import com.jiksoft.library.services.MembershipService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository repository;
    private final MembershipMapper mapper;
    private final LibraryRepository libraryRepository;

    @Override
    public MembershipBO create(MembershipBO membershipBo) {
        LibraryEntity library = libraryRepository.findById(UUID.fromString(membershipBo.getLibrary().getId()))
            .orElseThrow(() -> new EntityNotFoundException("Library with id " + membershipBo.getLibrary().getId() + " not found."));
        
        MembershipEntity entityToSave = mapper.fromBoToEntity(membershipBo);

        entityToSave.setLibrary(library);

        MembershipEntity saved = repository.save(entityToSave);
        return mapper.fromEntityToBo(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MembershipBO findById(String id) {
        UUID uuid = UUID.fromString(id);
        MembershipEntity entity = repository.findById(uuid)
            .orElseThrow(() -> new EntityNotFoundException("Membership not found"));
        return mapper.fromEntityToBo(entity);
    }

    @Override
    public List<MembershipBO> findAll() {
        List<MembershipEntity> list = repository.findAll();
        return mapper.fromEntityListToBoList(list);
    }

    @Override
    public MembershipBO update(String id, MembershipBO membershipBo) {
        LibraryEntity library = libraryRepository.findById(UUID.fromString(membershipBo.getLibrary().getId()))
            .orElseThrow(() -> new EntityNotFoundException("Library with id " + membershipBo.getLibrary().getId() + " not found."));

        MembershipEntity entity = repository.findById(UUID.fromString(id))
            .orElseThrow(() -> new EntityNotFoundException("Membership not found"));

        entity.setLibrary(library);
        
        mapper.fromBoToEntity(membershipBo, entity);
        repository.save(entity);
        return mapper.fromEntityToBo(entity);
    }

    @Override
    public void delete(String id) {
        MembershipEntity entity = repository.findById(UUID.fromString(id))
            .orElseThrow(() -> new EntityNotFoundException("Membership not found"));
        repository.delete(entity);
    }

    @Override
    public void validateUserMembership(String userId, String libraryId) throws MembershipException {
        UUID userUuid = UUID.fromString(userId);
        UUID libraryUuid = UUID.fromString(libraryId);
        repository.findByUserIdAndLibrary_Id(userUuid, libraryUuid)
            .orElseThrow(() -> new MembershipException("User does not have an active membership in this library"));
    }
}