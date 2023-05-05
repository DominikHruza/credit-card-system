package com.dhruza.creditcardapplicationapi.service;

import com.dhruza.creditcardapplicationapi.model.Applicant;


public interface ApplicantService {

    Applicant saveApplicant(Applicant applicant);
    Applicant getApplicantByOib(String oib);
    void deleteApplicantByOib(String oib);
}
