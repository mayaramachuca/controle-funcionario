package com.projeto.funcionariocontrole.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Departamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal budget;
    private BigDecimal totalSalarios;
    @OneToMany(mappedBy = "departamento" , cascade = CascadeType.ALL) //mapped qual é o lado inverso ou não dominante da relação.
    private List<Funcionario> funcionarios;


    public BigDecimal orcamentoDisponivel(){
        return budget.subtract(totalSalarios).setScale(2,RoundingMode.HALF_EVEN);
    }

    public void addSalario(BigDecimal valor){
        this.totalSalarios = this.totalSalarios.add(valor).setScale(2,RoundingMode.HALF_EVEN);
    }
}
