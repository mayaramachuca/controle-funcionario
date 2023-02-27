package com.projeto.funcionariocontrole.core.mapper;

import com.projeto.funcionariocontrole.app.dto.request.DepartamentoRequest;
import com.projeto.funcionariocontrole.app.dto.response.DepartamentoResponse;
import com.projeto.funcionariocontrole.core.builder.DepartamentoBuilder;
import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public final class DepartamentoMapper {

    private DepartamentoMapper(){
    }

    public static Departamento toEntity(DepartamentoRequest departamentoRequest){
        return DepartamentoBuilder.builder()
                .nome(departamentoRequest.getNome())
                .totalSalarios(BigDecimal.ZERO)
                .descricao(departamentoRequest.getDescricao())
                .budget(departamentoRequest.getBudget())
                .funcionarios(new ArrayList<Funcionario>())
                .build().toEntity();
    }

    public DepartamentoResponse toDto (Departamento departamento){
       FuncionarioMapper funcionarioMapper = new FuncionarioMapper();
        return DepartamentoResponse.builder()
                .nome(departamento.getNome())
                .id(departamento.getId())
                .totalSalarios(departamento.getTotalSalarios())
                .descricao(departamento.getDescricao())
                .budget(departamento.getBudget())
                .funcionarios(funcionarioMapper.toListDto(departamento.getFuncionarios()))
                .build();
    }

    public List<DepartamentoResponse> toListDto(List<Departamento> departamentos){
        List<DepartamentoResponse> departamentosDto = new ArrayList<>();
        for (Departamento departamento : departamentos) {
            DepartamentoResponse departamentoDto = toDto(departamento);
            departamentosDto.add(departamentoDto);
        }
        return departamentosDto;
    }
}
