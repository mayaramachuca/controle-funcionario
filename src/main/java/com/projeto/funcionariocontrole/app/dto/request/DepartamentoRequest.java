package com.projeto.funcionariocontrole.app.dto;

import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class DepartamentoRequest {
    private String nome;
    private String descricao;
    private BigDecimal budget;
}
