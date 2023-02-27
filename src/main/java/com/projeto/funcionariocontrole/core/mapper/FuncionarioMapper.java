package com.projeto.funcionariocontrole.core.mapper;

import com.projeto.funcionariocontrole.app.dto.request.FuncionarioRequest;
import com.projeto.funcionariocontrole.app.dto.response.FuncionarioResponse;
import com.projeto.funcionariocontrole.core.builder.FuncionarioBuilder;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import com.projeto.funcionariocontrole.domain.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuncionarioMapper {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Funcionario toEntity(FuncionarioRequest funcionarioRequest){

        var departamento =departamentoRepository
                .findById(funcionarioRequest.getIdDepartamento())
                .orElseThrow(()-> new RuntimeException("Departamento não encontrado"));

        return FuncionarioBuilder.builder()
                .cpf(funcionarioRequest.getCpf())
                .nome(funcionarioRequest.getNome())
                .salario(funcionarioRequest.getSalario())
                .dataAniversario(funcionarioRequest.getDataAniversario())
                .departamento(departamento)
                .build().toEntity();
    }
    private static FuncionarioResponse internalToDto(Funcionario funcionario){
        return FuncionarioResponse.builder()
                .cpf(funcionario.getCpf())
                .nome(funcionario.getNome())
                .dataAniversario(funcionario.getDataAniversario())
                .salario(funcionario.getSalario())
                .status(funcionario.getStatus())
                .idDepartamento(funcionario.getDepartamento().getId())
                .id(funcionario.getId())
                .build();
    }

    public FuncionarioResponse toDto(Funcionario funcionario){
        return FuncionarioResponse.builder()
                .cpf(funcionario.getCpf())
                .nome(funcionario.getNome())
                .dataAniversario(funcionario.getDataAniversario())
                .salario(funcionario.getSalario())
                .status(funcionario.getStatus())
                .idDepartamento(funcionario.getDepartamento().getId())
                .id(funcionario.getId())
                .build();
    }

    public List<FuncionarioResponse> toListDto(List<Funcionario> funcionarios) {

//        List<FuncionarioResponse> funcionariosDto = new ArrayList<>();
//        for (Funcionario funcionario : funcionarios) {
//            FuncionarioResponse funcionarioDto = toDto(funcionario);
//            funcionariosDto.add(funcionarioDto);
//        }
//        return funcionariosDto;


        return funcionarios.stream()
                .map(FuncionarioMapper::internalToDto)
                .collect(Collectors.toList());

    }
}
