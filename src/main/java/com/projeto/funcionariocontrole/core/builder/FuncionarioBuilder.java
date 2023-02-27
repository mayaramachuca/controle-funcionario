package com.projeto.funcionariocontrole.core.builder;

import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public class FuncionarioBuilder {

    private Long id;
    private String cpf;
    private String nome;
    private String dataAniversario;
    private BigDecimal salario;
    private Departamento departamento;
    private StatusFuncionario status;

    private LocalDate dataRejeicao;
    public Funcionario toEntity(){
        return new Funcionario(id, cpf, nome, dataAniversario, salario, departamento, status, dataRejeicao);
    }
}
