package com.dhruza.creditcardapplicationapi.repository;

import com.dhruza.creditcardapplicationapi.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}