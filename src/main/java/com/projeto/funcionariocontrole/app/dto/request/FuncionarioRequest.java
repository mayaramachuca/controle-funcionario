package com.projeto.funcionariocontrole.app.dto;

import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class FuncionarioRequest {

    private String cpf;
    private String nome;
    private String dataAniversario;
    private BigDecimal salario;
    private StatusFuncionario status;
    private Long idDepartamento;
}
