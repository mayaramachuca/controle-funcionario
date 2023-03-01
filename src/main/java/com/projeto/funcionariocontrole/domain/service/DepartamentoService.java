package com.projeto.funcionariocontrole.domain.service;

import com.projeto.funcionariocontrole.domain.entities.Departamento;

import java.util.List;

public interface DepartamentoService {
    Departamento save(Departamento departamento);
    Departamento getDepartamentoId(Long id);
    List<Departamento> getAllDepartamento();

    void deleteDepartamentoNome(String nome);
 //   Departamento updateDepartamento(Departamento departamento);

}
