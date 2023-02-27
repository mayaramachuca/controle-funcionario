package com.projeto.funcionariocontrole.app.dto.request;

import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequest {
    @CPF
    private String cpf;
    @NotBlank
    @Size(min=2, max=100)
    private String nome;
    @NotBlank
    @Size(min=10, max=10)
    private String dataAniversario;
    @NotNull
    private BigDecimal salario;
    @NotNull
    private Long idDepartamento;
}
