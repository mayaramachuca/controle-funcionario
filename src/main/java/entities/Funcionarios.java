package entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionarios {

    @Id
    @NonNull
    String cpf;
    @NonNull
    String nome;
    @NonNull
    String dataAniversario;
    @NonNull
    BigDecimal salario;
    @ManyToOne
    @NonNull
    Departamento departamentos;
    StatusFuncionario status;
}
