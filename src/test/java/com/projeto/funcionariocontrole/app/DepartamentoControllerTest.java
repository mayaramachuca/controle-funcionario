package com.projeto.funcionariocontrole.app;

import com.projeto.funcionariocontrole.app.dto.request.DepartamentoRequest;
import com.projeto.funcionariocontrole.app.dto.response.DepartamentoResponse;
import com.projeto.funcionariocontrole.core.mapper.DepartamentoMapper;
import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import com.projeto.funcionariocontrole.domain.service.DepartamentoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepartamentoControllerTest  {

    @MockBean
    public DepartamentoService service;
    @Autowired
    public DepartamentoMapper departamentoMapper;
    @InjectMocks
    public DepartamentoController contoller;

//    @Test
//    public void whenCreateDepartamentoIsOk() {
//
//        when(service.save(any())).thenReturn(Departamento.builder().build());
//
//        Funcionario funcionario = new Funcionario();
//        DepartamentoResponse departamento = contoller.createDepartamento(DepartamentoRequest.builder().build());
//
//       assertNotNull(departamento);
//    }
}
