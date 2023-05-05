package com.dhruza.creditcardapplicationapi.exception.handling;

import com.dhruza.creditcardapplicationapi.exception.ActiveApplicationFileException;
import com.dhruza.creditcardapplicationapi.exception.ApplicationFileException;
import com.dhruza.creditcardapplicationapi.exception.DataDoesNotExistException;
import com.dhruza.creditcardapplicationapi.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ApplicationFileControllerAdvice {

    @ExceptionHandler({
            ActiveApplicationFileException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onActiveApplicationFileException(
            Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return errorResponse;
    }

    @ExceptionHandler({
            ApplicationFileException.class,
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse onApplicationFileException(
            Exception e) {
        log.info("Error during file write: " + e.getMessage(), e.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Something went wrong during file saving. Contact support");
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return errorResponse;
    }
}
