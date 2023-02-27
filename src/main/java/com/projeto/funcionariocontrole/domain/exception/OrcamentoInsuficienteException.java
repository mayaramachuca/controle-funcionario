package com.projeto.funcionariocontrole.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class OrcamentoInsuficienteException extends RuntimeException {
    public OrcamentoInsuficienteException(String mensagem){
       super(mensagem);
    }
}
