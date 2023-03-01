package com.projeto.funcionariocontrole.app.dto.response;

import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import lombok.*;

import java.math.BigDecimal;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResponse {
    private Long id;
    private String cpf;
    private String nome;
    private String dataAniversario;
    private BigDecimal salario;
    private Long idDepartamento;
    private StatusFuncionario status;
}
