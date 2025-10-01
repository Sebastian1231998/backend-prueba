package com.jiksoft.library.repositories.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jiksoft.library.models.library.LibraryEntity;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntity, UUID> {
    
}
