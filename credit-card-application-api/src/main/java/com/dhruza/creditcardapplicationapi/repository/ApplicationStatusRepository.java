package com.dhruza.creditcardapplicationapi.repository;

import com.dhruza.creditcardapplicationapi.model.ApplicationStatus;
import com.dhruza.creditcardapplicationapi.model.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Long> {
    Optional<ApplicationStatus> findByStatusType(StatusType type);
}