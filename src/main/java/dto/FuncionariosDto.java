package dto;

import entities.Departamento;
import entities.Funcionarios;
import entities.StatusFuncionario;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@RequiredArgsConstructor
public class FuncionariosDto {
    String cpf;
    String nome;
    String dataAniversario;
    BigDecimal salario;
    Departamento departamentos;
    StatusFuncionario status;

    public FuncionariosDto(Funcionarios funcionarios){
        cpf = funcionarios.getCpf();
        nome = funcionarios.getNome();
        salario = funcionarios.getSalario();
        dataAniversario = funcionarios.getDataAniversario();
        departamentos = funcionarios.getDepartamentos();
        status = funcionarios.getStatus();
    }
}
