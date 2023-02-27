package com.projeto.funcionariocontrole.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor para o identificador é gerado aut. para cada registro inserido no banco
    private Long id;
    @Column(name = "CPF" , nullable = false , unique = true) //JPA quando for criar a tabela usar esse nome no campo de dados se não fica nome da classe
    private String cpf;
    private String nome;
    private String dataAniversario;
    private BigDecimal salario;
    @ManyToOne //Bidirecional significa que podemos acessar  func. de departamento e também departamento de func.
    @JoinColumn(name = "id_departamento",referencedColumnName = "id",nullable = false)        //esta anotação, estamos dizendo que na tabela Funcionario deve existir uma nova coluna, que vai se chamar departamento_id e que é chave estrangeira de Cesta.
    private Departamento departamento;
    private StatusFuncionario status;
    private LocalDate dataRejeicao;

}
