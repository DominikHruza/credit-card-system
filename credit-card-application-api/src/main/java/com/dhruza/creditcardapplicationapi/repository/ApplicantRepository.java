package com.dhruza.creditcardapplicationapi.repository;

import com.dhruza.creditcardapplicationapi.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<Applicant> findApplicantByOib(String oib);
}