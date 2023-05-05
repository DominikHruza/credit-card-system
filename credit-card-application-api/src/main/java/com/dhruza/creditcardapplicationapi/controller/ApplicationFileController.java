package com.dhruza.creditcardapplicationapi.controller;

import com.dhruza.creditcardapplicationapi.service.ApplicationFileFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/application-file")
public class ApplicationFileController {
    private final ApplicationFileFacade applicationFileFacade;

    @PostMapping("/generate/txt")
    public ResponseEntity<String> createTextFile(@RequestParam String oib){
        String filePath = applicationFileFacade.submitTxtApplication(oib);
        return ResponseEntity.status(HttpStatus.CREATED).body(filePath);
    }
}
