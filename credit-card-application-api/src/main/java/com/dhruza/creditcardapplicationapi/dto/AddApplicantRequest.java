package com.dhruza.creditcardapplicationapi.dto;

import com.dhruza.creditcardapplicationapi.dto.validation.ValidStatusType;
import com.dhruza.creditcardapplicationapi.model.StatusType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddApplicantRequest {
    @NotNull
    @NotBlank
    private String firstname;

    @NotNull
    @NotBlank
    private String lastname;

    @Length(min = 11, max = 11)
    private String oib;

    @ValidStatusType(enumClass = StatusType.class)
    @NotNull
    private String status;
}
