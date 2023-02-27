package com.projeto.funcionariocontrole.core;

import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import com.projeto.funcionariocontrole.domain.exception.CpfJaRegistradoException;
import com.projeto.funcionariocontrole.domain.exception.FuncionarioNaoEncontradoException;
import com.projeto.funcionariocontrole.domain.exception.OrcamentoInsuficienteException;
import com.projeto.funcionariocontrole.domain.repository.FuncionarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FuncionarioServiceImplTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioServiceImpl service;

    @Test
    public void SaveFuncionarioIsOk() {

        Funcionario funcionarioInput = Funcionario.builder()
                .cpf("12345678900")
                .dataAniversario("24/02/2023")
                .salario(BigDecimal.valueOf(1000.00))
                .nome("Jose")
                .status(StatusFuncionario.ATIVO)
                .departamento(Departamento.builder()
                        .budget(BigDecimal.valueOf(5000.00))
                        .totalSalarios(BigDecimal.ZERO)
                        .build())
                .build();

        Funcionario funcionarioExpected = Funcionario.builder()
                .cpf("12345678900")
                .dataAniversario("24/02/2023")
                .salario(BigDecimal.valueOf(1000.00))
                .nome("Jose")
                .id(1l)
                .status(StatusFuncionario.ATIVO)
                .departamento(Departamento.builder()
                        .budget(BigDecimal.valueOf(5000.00))
                        .totalSalarios(BigDecimal.ZERO)
                        .build())
                .build();

        when(funcionarioRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(funcionarioRepository.save(funcionarioInput)).thenReturn(funcionarioExpected);

        Funcionario response = service.save(funcionarioInput);
        assertEquals(funcionarioExpected,response);
    }

    @Test
    public void NotSaveFuncionarioCpf() {

        Funcionario funcionarioInput = Funcionario.builder()
                .cpf("12345678900")
                .dataAniversario("24/02/2023")
                .salario(BigDecimal.valueOf(1000.00))
                .nome("Jose")
                .status(StatusFuncionario.ATIVO)
                .departamento(Departamento.builder()
                        .budget(BigDecimal.valueOf(5000.00))
                        .totalSalarios(BigDecimal.ZERO)
                        .build())
                .build();

        when(funcionarioRepository.findByCpf(anyString())).thenReturn(Optional.of(funcionarioInput));
        assertThrows(CpfJaRegistradoException.class,()->service.save(funcionarioInput));
    }

    @Test
    public void NotSaveFuncionarioSalario() {

        Funcionario funcionarioInput = Funcionario.builder()
                .cpf("12345678900")
                .dataAniversario("24/02/2023")
                .salario(BigDecimal.valueOf(3000.00))
                .nome("Jose")
                .status(StatusFuncionario.ATIVO)
                .departamento(Departamento.builder()
                        .budget(BigDecimal.valueOf(2000.00))
                        .totalSalarios(BigDecimal.ZERO)
                        .build())
                .build();

        Funcionario funcionarioExpected = Funcionario.builder()
                .cpf("12345678900")
                .dataAniversario("24/02/2023")
                .salario(BigDecimal.valueOf(3000.00))
                .dataRejeicao(LocalDate.now())
                .nome("Jose")
                .id(1l)
                .status(StatusFuncionario.REJEITADO_FALTA_RECURSO)
                .departamento(Departamento.builder()
                        .budget(BigDecimal.valueOf(2000.00))
                        .totalSalarios(BigDecimal.ZERO)
                        .build())
                .build();

        when(funcionarioRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(funcionarioRepository.save(funcionarioInput)).thenReturn(funcionarioExpected);
        Funcionario response = service.save(funcionarioInput);
        assertEquals(funcionarioExpected,response);
    }

    @Test
    public void GetAllFuncionarios() {
        Funcionario funcionarioInput = Funcionario.builder().build();
        when(funcionarioRepository.findAll()).thenReturn(Arrays.asList(funcionarioInput));
        List<Funcionario> funcionarios = service.getAllFuncionarios();
        assertEquals(funcionarioInput,funcionarios.get(0));
    }

    @Test
    public void GetFuncionarioId() {
        Funcionario funcionarioInput = Funcionario
                .builder()
                .id(1l)
                .build();
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionarioInput));
        Funcionario funcionario = service.getFuncionarioId(funcionarioInput.getId()).get();
        assertEquals(funcionarioInput,funcionario);
    }

    @Test
    public void NotGetFuncionarioId() {
        Funcionario funcionarioInput = Funcionario
                .builder()
                .id(1l)
                .build();
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(FuncionarioNaoEncontradoException.class,()->service.getFuncionarioId(funcionarioInput.getId()));
    }

    @Test
    public void aumentoSalarioIsOk(){
        Funcionario funcionarioInput = Funcionario.builder()
                .cpf("12345678900")
                .dataAniversario("24/02/2023")
                .salario(BigDecimal.valueOf(1000.00))
                .nome("Jose")
                .id((1l))
                .status(StatusFuncionario.ATIVO)
                .departamento(Departamento.builder()
                        .budget(BigDecimal.valueOf(5000.00))
                        .totalSalarios(BigDecimal.ZERO)
                        .build())
                .build();

        Funcionario funcionarioExpected = Funcionario.builder()
                .cpf("12345678900")
                .dataAniversario("24/02/2023")
                .salario(BigDecimal.valueOf(2000.00))
                .nome("Jose")
                .id(1l)
                .status(StatusFuncionario.ATIVO)
                .departamento(Departamento.builder()
                        .budget(BigDecimal.valueOf(5000.00))
                        .totalSalarios(BigDecimal.ZERO)
                        .build())
                .build();

        when(funcionarioRepository.findById(1l)).thenReturn(Optional.of(funcionarioInput));
        when(funcionarioRepository.save(any())).thenReturn(funcionarioExpected);

        Funcionario response = service.aumentoSalario(funcionarioExpected.getId(), BigDecimal.valueOf(1000));
        assertEquals(funcionarioExpected,response);
    }

    @Test
    public void aumentoSalarioSemOrcamento(){
        Funcionario funcionarioInput = Funcionario.builder()
                .cpf("12345678900")
                .dataAniversario("24/02/2023")
                .salario(BigDecimal.valueOf(1500.00))
                .nome("Jose")
                .id((1l))
                .status(StatusFuncionario.ATIVO)
                .departamento(Departamento.builder()
                        .budget(BigDecimal.valueOf(1500.00))
                        .totalSalarios(BigDecimal.valueOf(1500.00))
                        .build())
                .build();

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionarioInput));

        assertThrows(OrcamentoInsuficienteException.class,()->service.aumentoSalario(funcionarioInput.getId(), BigDecimal.valueOf(1000.00)));
    }

}
