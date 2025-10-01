package com.jiksoft.library.repositories.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jiksoft.library.models.library.MembershipEntity;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity, UUID> {

    Optional<MembershipEntity> findByUserIdAndLibrary_Id(UUID userId, UUID libraryId);

    List<MembershipEntity> findByUserId(UUID userId);
}