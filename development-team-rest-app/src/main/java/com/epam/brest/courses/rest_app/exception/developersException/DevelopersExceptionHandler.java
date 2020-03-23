package com.epam.brest.courses.rest_app.exception.developersException;

import com.epam.brest.courses.rest_app.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * DevelopersExceptionHandler
 */
public class DevelopersExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * String INCORRECT_REQUEST.
     */
    private String INCORRECT_REQUEST = "INCORRECT_REQUEST";


    /**
     * Returns new ResponseEntity<>(error, HttpStatus.NOT_FOUND).
     *
     * @param ex DevelopersNotFoundException ex.
     * @param request WebRequest request.
     * @return new ResponseEntity<>(error, HttpStatus.NOT_FOUND).
     */
    @ExceptionHandler(DevelopersNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleDevelopersNotFoundException (DevelopersNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
