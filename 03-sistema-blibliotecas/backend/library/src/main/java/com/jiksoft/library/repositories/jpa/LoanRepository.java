package com.jiksoft.library.repositories.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jiksoft.library.models.library.BookEntity;

import com.jiksoft.library.models.library.LoanEntity;


@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, UUID> {

    // Buscar préstamos activos de un libro
    Optional<LoanEntity> findByBookAndReturnDateIsNull(BookEntity book);

    // Buscar préstamos activos de un usuario
    List<LoanEntity> findByUserIdAndReturnDateIsNull(UUID userId);

    // Buscar préstamo activo por libro y usuario
    Optional<LoanEntity> findByUserIdAndBookAndReturnDateIsNull(UUID userId, BookEntity book);
}
