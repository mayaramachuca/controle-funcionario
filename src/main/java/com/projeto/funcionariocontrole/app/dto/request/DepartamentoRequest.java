package com.projeto.funcionariocontrole.app.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
public class DepartamentoRequest {
    @NotBlank
    @Size(min=2, max=100)
    private String nome;
    @NotBlank
    @Size(min=2, max=100)
    private String descricao;
    @NotNull
    private BigDecimal budget;
}
