package com.mehr.SpringBootAdvanced.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request){
        ErrorDetails errorDetails= new ErrorDetails(LocalDateTime.now(),ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundExceptions(Exception ex, WebRequest request){
        ErrorDetails errorDetails= new ErrorDetails(LocalDateTime.now(),ex.getMessage(), "This is for none existing user");

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorDetails errorDetails= new ErrorDetails(LocalDateTime.now(),ex.getMessage(), "This is for invalid details provided for a user");
        StringBuffer messageBuffer= new StringBuffer ();
        ex.getFieldErrors().stream().forEach(error -> {
                    messageBuffer.append (error.getDefaultMessage());
                    messageBuffer.append(System.getProperty("line.separator"));
                }
            );
        errorDetails.setMessage(messageBuffer.toString());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
