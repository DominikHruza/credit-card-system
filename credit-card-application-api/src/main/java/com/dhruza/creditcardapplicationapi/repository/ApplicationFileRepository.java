package com.dhruza.creditcardapplicationapi.repository;

import com.dhruza.creditcardapplicationapi.model.ApplicationFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationFileRepository extends JpaRepository<ApplicationFile, Long> {
}