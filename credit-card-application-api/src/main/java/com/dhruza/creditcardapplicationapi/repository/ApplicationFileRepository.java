package com.dhruza.creditcardapplicationapi.repository;

import com.dhruza.creditcardapplicationapi.model.ApplicationFile;
import com.dhruza.creditcardapplicationapi.model.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ApplicationFileRepository extends JpaRepository<ApplicationFile, Long> {
    @Query("""
    SELECT af FROM ApplicationFile af
    WHERE af.applicant.id = :applicantId
    AND af.fileStatus.statusType = :statusType""")
    Set<ApplicationFile> findApplicationFilesByApplicantAndStatus(Long applicantId, StatusType statusType);

    Set<ApplicationFile> findByApplicantId(Long applicantId);
}