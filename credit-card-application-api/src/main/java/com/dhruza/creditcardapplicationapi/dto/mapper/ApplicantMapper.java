package com.dhruza.creditcardapplicationapi.dto.mapper;

import com.dhruza.creditcardapplicationapi.dto.AddApplicantRequest;
import com.dhruza.creditcardapplicationapi.dto.ApplicantResponse;
import com.dhruza.creditcardapplicationapi.model.Applicant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApplicantMapper {

    @Mapping(target = "status", ignore = true)
    Applicant convertFromDto(AddApplicantRequest applicantRequest);

    @Mapping(source = "applicant.status.statusType.name", target = "status")
    ApplicantResponse convertToDto(Applicant applicant);
}
