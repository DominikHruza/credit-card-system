package com.dhruza.creditcardapplicationapi.service;

import com.dhruza.creditcardapplicationapi.model.Applicant;
import com.dhruza.creditcardapplicationapi.model.ApplicationFile;
import com.dhruza.creditcardapplicationapi.model.StatusType;

import java.util.Set;

public interface ApplicationFileService {
    String generateTxtFile(Applicant applicant);
    ApplicationFile persistFileMetadata(ApplicationFile file);
    Set<ApplicationFile> findAllFilesByApplicantAndStatus(Long id, StatusType statusType);
    void removeApplicantFromApplicationFile(Applicant applicant);
}
