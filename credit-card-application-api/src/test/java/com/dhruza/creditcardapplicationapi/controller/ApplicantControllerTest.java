package com.dhruza.creditcardapplicationapi.controller;

import com.dhruza.creditcardapplicationapi.dto.AddApplicantRequest;
import com.dhruza.creditcardapplicationapi.dto.ApplicantResponse;
import com.dhruza.creditcardapplicationapi.dto.mapper.ApplicantMapper;
import com.dhruza.creditcardapplicationapi.model.Applicant;
import com.dhruza.creditcardapplicationapi.model.ApplicationStatus;
import com.dhruza.creditcardapplicationapi.model.StatusType;
import com.dhruza.creditcardapplicationapi.service.ApplicantService;
import com.dhruza.creditcardapplicationapi.service.ApplicationFileFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ApplicantController.class)
class ApplicantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ApplicantMapper applicantMapperMock;

    @MockBean
    private ApplicantService applicantServiceMock;

    @MockBean
    private ApplicationFileFacade applicationFileFacadeMock;

    @Test
    void findApplicant_shouldReturn_applicantResponse_when_oibProvided() throws Exception {
        ApplicantResponse applicantResponse = ApplicantResponse.builder()
                .firstname("Pero")
                .lastname("Peric")
                .oib("12345678901")
                .status("ACTIVE")
                .build();

        given(applicantServiceMock.getApplicantByOib(anyString()))
                .willReturn(Applicant.builder().build());
        given(applicantMapperMock.convertToDto(any(Applicant.class)))
                .willReturn(applicantResponse);

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/applicant?oib=12345678901"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualTo(
                objectMapper.writeValueAsString(applicantResponse));

    }

    @Test
    void saveApplicant_shouldReturn_applicantResponse_when_requestBodyValid() throws Exception {
        AddApplicantRequest addApplicantRequest = AddApplicantRequest.builder()
                .firstname("Pero")
                .lastname("Peric")
                .oib("12345678901")
                .status("ACTIVE")
                .build();

        Applicant applicant = Applicant.builder()
                .build();

        ApplicantResponse applicantResponse = ApplicantResponse.builder()
                .firstname("Pero")
                .lastname("Peric")
                .oib("12345678901")
                .status("ACTIVE")
                .build();

        given(applicantMapperMock.convertFromDto(any(AddApplicantRequest.class)))
                .willReturn(applicant);

        given(applicantServiceMock.saveApplicant(any(Applicant.class)))
                .willReturn(applicant);

        given(applicantMapperMock.convertToDto(any(Applicant.class)))
                .willReturn(applicantResponse);


        MvcResult mvcResult = mockMvc.perform(post("/api/applicant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addApplicantRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualTo(
                objectMapper.writeValueAsString(applicantResponse));

    }

    @ParameterizedTest
    @CsvSource({
        "pero, peric, 12345678901, TEST",
        "'', peric, 12345678901, ACTIVE",
        "pero, '', 12345678901, ACTIVE",
        "pero, peric, 1234, ACTIVE",
    })
    void addApplicant_ShouldThrow_badRequest_when_requestBodyNotValid(
            String firstnanme, String lastname, String oib, String status) throws Exception {

        AddApplicantRequest addApplicantRequest = AddApplicantRequest.builder()
                .firstname(firstnanme)
                .lastname(lastname)
                .oib(oib)
                .status(status)
                .build();

        Applicant applicant = Applicant
                .builder()
                .build();

        ApplicantResponse applicantResponse = ApplicantResponse.builder()
                .firstname("Pero")
                .lastname("Peric")
                .oib("12345678901")
                .status("ACTIVE")
                .build();

        given(applicantMapperMock.convertFromDto(any(AddApplicantRequest.class)))
                .willReturn(applicant);

        given(applicantServiceMock.saveApplicant(any(Applicant.class)))
                .willReturn(applicant);

        given(applicantMapperMock.convertToDto(any(Applicant.class)))
                .willReturn(applicantResponse);

        mockMvc.perform(post("/api/applicant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addApplicantRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    void deleteApplicant_shouldReturn_200_when_userDeletedByOib() throws Exception {
         mockMvc.perform(delete("/api/applicant?oib=12345678901"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}