package com.dhruza.creditcardapplicationapi.service;

import com.dhruza.creditcardapplicationapi.model.Applicant;
import org.springframework.stereotype.Service;


public interface ApplicantService {

    Applicant saveApplicant(Applicant applicant);
}
