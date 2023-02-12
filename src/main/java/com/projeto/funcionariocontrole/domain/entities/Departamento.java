package com.projeto.funcionariocontrole.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Departamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {

    @Id
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal budget;
    @OneToMany(mappedBy = "departamento" , cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

}
