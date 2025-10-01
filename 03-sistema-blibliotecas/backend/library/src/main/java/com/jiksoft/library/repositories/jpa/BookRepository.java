package com.jiksoft.library.repositories.jpa;

import com.jiksoft.library.models.library.BookEntity;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    @Query("SELECT b FROM BookEntity b WHERE b.library.id = :libraryId AND b.deletedAt IS NULL")
    List<BookEntity> findDeletedBooksByLibrary(@Param("libraryId") UUID libraryId);
        
}
