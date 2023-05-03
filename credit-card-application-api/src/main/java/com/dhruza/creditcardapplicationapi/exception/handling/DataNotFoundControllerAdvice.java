package com.dhruza.creditcardapplicationapi.exception.handling;

import com.dhruza.creditcardapplicationapi.exception.DataDoesNotExistException;
import com.dhruza.creditcardapplicationapi.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DataNotFoundControllerAdvice {

    @ExceptionHandler({
            DataDoesNotExistException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onException(
            Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return errorResponse;
    }

//    @ExceptionHandler({ConstraintViolationException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public Set<String> handleValidationExceptions(ConstraintViolationException ex) {
//        Set<String> errors = new HashSet<>();
//        ex.getConstraintViolations().forEach(error ->
//                errors.add(error.getMessage()));
//        return errors;
//    }
}