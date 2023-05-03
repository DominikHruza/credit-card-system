package com.dhruza.creditcardapplicationapi.controller;

import com.dhruza.creditcardapplicationapi.dto.AddApplicantRequest;
import com.dhruza.creditcardapplicationapi.dto.mapper.ApplicantMapper;
import com.dhruza.creditcardapplicationapi.model.Applicant;
import com.dhruza.creditcardapplicationapi.model.ApplicationStatus;
import com.dhruza.creditcardapplicationapi.model.StatusType;
import com.dhruza.creditcardapplicationapi.service.ApplicantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping("/api/applicant")
public class ApplicantController {

    private final ApplicantMapper applicantMapper;
    private final ApplicantService applicantService;

    @GetMapping()
    public ResponseEntity<String> getAll(){

        return ResponseEntity.ok("hello");
    }

    @PostMapping()
    public ResponseEntity<Applicant> saveApplicant(@Valid @RequestBody AddApplicantRequest applicantRequest){
        Applicant applicant = applicantMapper.convertFromDto(applicantRequest);
        applicant.setStatus(ApplicationStatus.
                builder()
                .statusType(StatusType.valueOf(applicantRequest.getStatus()))
                .build());
        Applicant newApplicant = applicantService.saveApplicant(applicant);

        return ResponseEntity.ok(newApplicant);
    }
}
