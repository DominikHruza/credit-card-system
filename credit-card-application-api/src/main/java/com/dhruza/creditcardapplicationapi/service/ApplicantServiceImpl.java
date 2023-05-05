package com.dhruza.creditcardapplicationapi.service;

import com.dhruza.creditcardapplicationapi.exception.DataDoesNotExistException;
import com.dhruza.creditcardapplicationapi.exception.DuplicateEntryException;
import com.dhruza.creditcardapplicationapi.model.Applicant;
import com.dhruza.creditcardapplicationapi.model.ApplicationStatus;
import com.dhruza.creditcardapplicationapi.model.StatusType;
import com.dhruza.creditcardapplicationapi.repository.ApplicantRepository;
import com.dhruza.creditcardapplicationapi.repository.ApplicationFileRepository;
import com.dhruza.creditcardapplicationapi.repository.ApplicationStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicationStatusRepository applicationStatusRepository;
    private final ApplicationFileRepository applicationFileRepository;

    @Transactional
    public Applicant saveApplicant(Applicant applicant) {
        StatusType statusType = applicant.getStatus()
                .getStatusType();


        Optional<ApplicationStatus> statusFound = applicationStatusRepository
                .findByStatusType(statusType);

        ApplicationStatus applicationStatus = statusFound.orElseThrow(
                () -> new DataDoesNotExistException(
                        "Application status with name " + statusType + " not found!"));

        applicant.setStatus(applicationStatus);

        try {
            return applicantRepository.save(applicant);
        } catch(Exception e){
            throw new DuplicateEntryException("Duplicate entry.");
        }

    }

    @Transactional(readOnly = true)
    public Applicant getApplicantByOib(String oib) {
        Optional<Applicant> applicantFound = applicantRepository.findApplicantByOib(oib);
        return applicantFound.orElseThrow(
                () -> new DataDoesNotExistException(
                        "Applicant with OIB " + oib + " not found!"));
    }

    @Transactional()
    public void deleteApplicantByOib(String oib) {
        Applicant applicantFound = getApplicantByOib(oib);
        applicantRepository.delete(applicantFound);
    }
}
