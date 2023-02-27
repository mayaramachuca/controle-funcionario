package com.projeto.funcionariocontrole.app.dto;

import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import lombok.*;

import java.math.BigDecimal;
@Builder
@Data
public class FuncionarioResponse {
    private Long id;
    private String cpf;
    private String nome;
    private String dataAniversario;
    private BigDecimal salario;
    private Long IdDepartamento;
    private StatusFuncionario status;
}
