package com.br.api.algafood.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Error error = new Error();
        List<Error.Campo> campos = new ArrayList<>();

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
}
