package io.zipcoder.tc_spring_poll_application.handler;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import dtos.error.ErrorDetail;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;


@ControllerAdvice
public class RestExceptionHandler {


    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourdeNotFoundException(ResourceNotFoundException
    rnfe, HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }


} // TODO - END OF CLASS
