package com.dhruza.creditcardapplicationapi.service;

import com.dhruza.creditcardapplicationapi.exception.DataDoesNotExistException;
import com.dhruza.creditcardapplicationapi.model.Applicant;
import com.dhruza.creditcardapplicationapi.model.ApplicationStatus;
import com.dhruza.creditcardapplicationapi.model.StatusType;
import com.dhruza.creditcardapplicationapi.repository.ApplicantRepository;
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

    @Transactional
    public Applicant saveApplicant(Applicant applicant) {
        StatusType statusType = applicant.getStatus()
                .getStatusType();


        Optional<ApplicationStatus> statusFound = applicationStatusRepository
                .findByStatusType(statusType);

        applicant.setStatus(statusFound.orElseThrow(() -> new DataDoesNotExistException(
                "Application status with name " + statusType + " not found!"
        )));

        return applicantRepository.save(applicant);
    }
}
