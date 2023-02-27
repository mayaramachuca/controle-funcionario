package com.projeto.funcionariocontrole.core;

import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import com.projeto.funcionariocontrole.domain.exception.DepartamentoJaRegistradoException;
import com.projeto.funcionariocontrole.domain.repository.DepartamentoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepartamentoServiceImplTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private DepartamentoServiceImpl service;

    @Test
    public void GetDepartamentoId() {
        Departamento departamentoInput = Departamento
                .builder()
                .id(1l)
                .build();
        when(departamentoRepository.findById(anyLong())).thenReturn(Optional.of(departamentoInput));
        Departamento departamento = service.getDepartamentoId(departamentoInput.getId());
        assertEquals(departamentoInput,departamento);
    }

    @Test
    public void GetAllDepartamentos() {
        Departamento departamentoInput = Departamento.builder().build();
        when(departamentoRepository.findAll()).thenReturn(Arrays.asList(departamentoInput));
        List<Departamento> departamentos = service.getAllDepartamento();
        assertEquals(departamentoInput,departamentos.get(0));
    }

    @Test
    public void DeleteDepartamento() {
        Departamento departamentoInput = Departamento
                .builder()
                .nome("Teste1")
                .build();
        when(departamentoRepository.findByNome(anyString())).thenReturn(Optional.of(departamentoInput));
        service.deleteDepartamentoNome("Teste1");
        assertNotNull(departamentoInput);
    }

    @Test
    public void SaveDepartamentoIsOk() {

        Departamento departamentoInput = Departamento
                .builder()
                .nome("Departamento 1")
                .descricao("Departamento 1")
                .budget(BigDecimal.valueOf(2000.00))
                .totalSalarios(BigDecimal.valueOf(1500.00))
                .build();

        Departamento departamentoExpected = Departamento
                .builder()
                .id(1l)
                .nome("Departamento 1")
                .descricao("Departamento 1")
                .budget(BigDecimal.valueOf(2000.00))
                .totalSalarios(BigDecimal.valueOf(1500.00))
                .build();

        when(departamentoRepository.findByNome(anyString())).thenReturn(Optional.empty());
        when(departamentoRepository.save(departamentoInput)).thenReturn(departamentoExpected);
        Departamento response = service.save(departamentoInput);
        assertEquals(departamentoExpected, response);
    }

    @Test
    public void NotSaveDepartamento(){
        Departamento departamentoInput = Departamento
                .builder()
                .nome("Departamento 1")
                .descricao("Departamento 1")
                .budget(BigDecimal.valueOf(2000.00))
                .totalSalarios(BigDecimal.valueOf(1500.00))
                .build();

        when(departamentoRepository.findByNome(anyString())).thenReturn(Optional.of(departamentoInput));
        assertThrows(DepartamentoJaRegistradoException.class, ()-> service.save(departamentoInput));
    }
}
