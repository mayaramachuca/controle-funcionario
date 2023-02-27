package com.projeto.funcionariocontrole.app.exceptionHandler;

import com.projeto.funcionariocontrole.domain.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CpfJaRegistradoException.class)
    public ResponseEntity<Object> cpfJaRegistradoHandler(CpfJaRegistradoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        Problema problema = setProblema(ex.getMessage(), status);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DepartamentoJaRegistradoException.class)
    public ResponseEntity<Object> departamentoJaRegistradoHandler(DepartamentoJaRegistradoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        Problema problema = setProblema(ex.getMessage(), status);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
    @ExceptionHandler(OrcamentoInsuficienteException.class)
    public  ResponseEntity<Object> orcamentoInsuficienteHandler(OrcamentoInsuficienteException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        Problema problema = setProblema(ex.getMessage(), status);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DepartamentoNaoEncontradoException.class)
    public ResponseEntity<Object> departamentoNaoEncontradoHandler(DepartamentoNaoEncontradoException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problema problema = setProblema(ex.getMessage(), status);
        return  handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<Object> funcionarioNaoEncontradoHandler(FuncionarioNaoEncontradoException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problema problema = setProblema(ex.getMessage(), status);
        return  handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        var campos = new ArrayList<Problema.Campo>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            campos.add(new Problema.Campo(nome, mensagem));
        }
        var problema = setProblema("Um ou mais campos não foram preenchidos corretamente.", status);
        problema.setCampos(campos);
        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    private Problema setProblema(String title, HttpStatus status){

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitle(title);
        problema.setOffsetDateTime(OffsetDateTime.now());

        return problema;
    }
}
