package com.ocamento.orcamentosite.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidacaoExceptionHandler {

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<?> tratarErroValidacao( MethodArgumentNotValidException exception ) {

        Map<String, String> erros = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach( erro -> {
            erros.put( erro.getField(), erro.getDefaultMessage() );
        } );
        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( erros );
    }
}
