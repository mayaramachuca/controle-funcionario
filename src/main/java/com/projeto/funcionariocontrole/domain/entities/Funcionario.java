package com.projeto.funcionariocontrole.domain.entities;

import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CPF" , nullable = false , unique = true)
    private String cpf;
    private String nome;
    private String dataAniversario;
    private BigDecimal salario;
    @ManyToOne
    @JoinColumn(name = "id_departamento",referencedColumnName = "id",nullable = false)
    private Departamento departamento;
    private StatusFuncionario status;
}
