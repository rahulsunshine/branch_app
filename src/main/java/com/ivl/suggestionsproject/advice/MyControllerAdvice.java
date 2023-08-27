package com.ivl.suggestionsproject.advice;

import com.ivl.suggestionsproject.custom.exception.EmployeePortalListEmptyException;
import com.ivl.suggestionsproject.custom.exception.EmptyInputException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {
        return new ResponseEntity<>("Input fields are empty,please look into it", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        return new ResponseEntity<>("No record is present in database ,please change your request", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeePortalListEmptyException.class)
    public ResponseEntity<String> handleEmptyDatabaseException(EmployeePortalListEmptyException employeePortalListEmptyException) {
        return new ResponseEntity<>("Database is empty", HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("please your HTTP method type", HttpStatus.NOT_FOUND);
    }
}
