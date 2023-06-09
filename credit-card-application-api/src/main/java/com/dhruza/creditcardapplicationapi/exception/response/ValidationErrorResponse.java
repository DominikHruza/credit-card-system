package com.dhruza.creditcardapplicationapi.exception.response;

import com.dhruza.creditcardapplicationapi.dto.validation.Violation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationErrorResponse {
    private List<Violation> violations = new ArrayList<>();
}
