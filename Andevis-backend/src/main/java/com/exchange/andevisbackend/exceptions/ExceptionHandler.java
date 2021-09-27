package com.exchange.andevisbackend.exceptions;

import com.exchange.andevisbackend.DTO.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Response> handleException (Exception e){

        Response response = new Response();

        if(e.getClass().getName().equals(NoCurrencyDataException.class.getName())){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        else if(e.getClass().getName().equals(NoUserFoundException.class.getName())||
        e.getClass().getName().equals(SignatureException.class.getName())){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
        }

        else if(e.getClass().getName().equals(XMLParseException.class.getName())){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMessage("Error occurred during request handling with root cause "+e.getCause());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
