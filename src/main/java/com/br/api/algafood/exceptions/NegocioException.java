package com.br.api.algafood.exceptions;

public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NegocioException(String message){
        super(message);
    }

}
