package com.dhruza.creditcardapplicationapi.service;

public interface ApplicationFileFacade {
    String submitTxtApplication(String oib);
    void handleApplicantDeletion(String oib);
}
