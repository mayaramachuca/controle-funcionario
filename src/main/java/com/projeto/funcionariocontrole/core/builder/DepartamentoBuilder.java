package com.projeto.funcionariocontrole.core.builder;

import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public class DepartamentoBuilder {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal budget;
    private BigDecimal totalSalarios;
    private List<Funcionario> funcionarios;
    public Departamento toEntity(){
        return new Departamento(id, nome, descricao, budget, totalSalarios, funcionarios );
    }
}
