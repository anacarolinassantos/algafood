package com.br.api.algafood.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    static Error error = new Error();
    static List<Error.Campo> campos = new ArrayList<>();

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        for (ObjectError errors : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) errors).getField();
            String mensagem = errors.getDefaultMessage();
            campos.add(new Error.Campo(nome,mensagem));
        }
        error.setStatus(status.value());
        error.setDataHora(LocalDateTime.now());
        error.setTitulo("Campo inválido. Faça o preenchimento correto e tente novamente");
        error.setCampos(campos);
        return handleExceptionInternal(ex, error,headers, status, request);
    }
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setStatus(status.value());
        error.setDataHora(LocalDateTime.now());
        error.setTitulo(ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}
