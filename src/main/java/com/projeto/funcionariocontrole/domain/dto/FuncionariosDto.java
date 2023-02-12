package com.projeto.funcionariocontrole.domain.dto;

import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class FuncionariosDto {
    String cpf;
    String nome;
    String dataAniversario;
    BigDecimal salario;
    Departamento departamentos;
    StatusFuncionario status;

    public FuncionariosDto(Funcionario funcionario){
        cpf = funcionario.getCpf();
        nome = funcionario.getNome();
        salario = funcionario.getSalario();
        dataAniversario = funcionario.getDataAniversario();
        departamentos = funcionario.getDepartamento();
        status = funcionario.getStatus();
    }
}
