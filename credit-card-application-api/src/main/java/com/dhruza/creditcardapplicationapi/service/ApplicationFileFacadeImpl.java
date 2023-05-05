package com.dhruza.creditcardapplicationapi.service;

import com.dhruza.creditcardapplicationapi.exception.ActiveApplicationFileException;
import com.dhruza.creditcardapplicationapi.model.Applicant;
import com.dhruza.creditcardapplicationapi.model.ApplicationFile;
import com.dhruza.creditcardapplicationapi.model.StatusType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ApplicationFileFacadeImpl implements ApplicationFileFacade {
    private final ApplicantService applicantService;
    private final ApplicationFileService applicationFileService;

    public String submitTxtApplication(String oib) {
        Applicant applicant = applicantService.getApplicantByOib(oib);
        checkAndThrowIfActiveApplicationExists(applicant);
        String applicationFilePath = applicationFileService.generateTxtFile(applicant);

        ApplicationFile applicationFile = ApplicationFile.builder()
                .applicant(applicant)
                .fileStatus(applicant.getStatus())
                .locationUrl(applicationFilePath).build();

        applicationFileService.persistFileMetadata(applicationFile);

        return applicationFilePath;
    }

    public void handleApplicantDeletion(String oib) {
        Applicant applicant = applicantService.getApplicantByOib(oib);
        applicationFileService.removeApplicantFromApplicationFile(applicant);
        applicantService.deleteApplicantByOib(oib);
    }

    private void checkAndThrowIfActiveApplicationExists(Applicant applicant) {
        Set<ApplicationFile> activeApplicationFiles =
                applicationFileService.findAllFilesByApplicantAndStatus(
                        applicant.getId(), StatusType.ACTIVE);

        if(!activeApplicationFiles.isEmpty()){
            throw new ActiveApplicationFileException(
                    "The user already has active application.");
        }
    }
}
