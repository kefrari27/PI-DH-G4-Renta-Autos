package com.dh.backend_G4.exceptions;

import org.apache.log4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {

    private static final Logger  logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<?> allErrors(Exception ex, WebRequest req){
        logger.error(ex.getMessage());
        return new ResponseEntity<>("Error"+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest req){
        logger.error(ex.getMessage());
        return new ResponseEntity<>("Error "+ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<?> badRequestException(BadRequestException ex, WebRequest req){
        logger.error(ex.getMessage());
        return new ResponseEntity<>("Error "+ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
