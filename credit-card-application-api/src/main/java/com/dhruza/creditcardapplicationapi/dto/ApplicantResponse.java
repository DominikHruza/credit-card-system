package com.dhruza.creditcardapplicationapi.dto;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicantResponse {
    private String firstname;
    private String lastname;
    private String oib;
    private String status;
}
