package com.dhruza.creditcardapplicationapi.controller;

import com.dhruza.creditcardapplicationapi.dto.AddApplicantRequest;
import com.dhruza.creditcardapplicationapi.dto.ApplicantResponse;
import com.dhruza.creditcardapplicationapi.dto.mapper.ApplicantMapper;
import com.dhruza.creditcardapplicationapi.model.Applicant;
import com.dhruza.creditcardapplicationapi.model.ApplicationStatus;
import com.dhruza.creditcardapplicationapi.model.StatusType;
import com.dhruza.creditcardapplicationapi.service.ApplicantService;
import com.dhruza.creditcardapplicationapi.service.ApplicationFileFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping("/api/applicant")
public class ApplicantController {

    private final ApplicantMapper applicantMapper;
    private final ApplicantService applicantService;
    private final ApplicationFileFacade applicationFileFacade;

    @GetMapping()
    public ResponseEntity<ApplicantResponse> findApplicant(@RequestParam String oib){
        Applicant applicant = applicantService.getApplicantByOib(oib);
        ApplicantResponse applicantResponse = applicantMapper.convertToDto(applicant);

        return ResponseEntity.ok(applicantResponse);
    }

    @PostMapping()
    public ResponseEntity<ApplicantResponse> saveApplicant(@Valid @RequestBody AddApplicantRequest applicantRequest){
        Applicant applicant = applicantMapper.convertFromDto(applicantRequest);

        applicant.setStatus(ApplicationStatus.
                builder()
                .statusType(StatusType.valueOf(applicantRequest.getStatus()))
                .build());

        Applicant newApplicant = applicantService.saveApplicant(applicant);
        ApplicantResponse applicantResponse = applicantMapper.convertToDto(newApplicant);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicantResponse);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteApplicant(@RequestParam String oib){
        applicationFileFacade.handleApplicantDeletion(oib);
        return ResponseEntity.ok().build();
    }
}
