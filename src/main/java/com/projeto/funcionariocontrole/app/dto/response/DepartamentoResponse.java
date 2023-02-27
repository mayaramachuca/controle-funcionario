package com.projeto.funcionariocontrole.app.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class DepartamentoResponse {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal budget;
    private BigDecimal totalSalarios;
    private List<FuncionarioResponse> funcionarios;

}
