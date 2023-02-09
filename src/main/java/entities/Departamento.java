package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name="Departamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {

    @Id
    String nome;
    String descricao;
    Budget budget;

}
