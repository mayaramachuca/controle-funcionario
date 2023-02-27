package com.projeto.funcionariocontrole.domain.service;

import com.projeto.funcionariocontrole.domain.entities.Funcionario;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface FuncionarioService {

        Optional<Funcionario> getFuncionarioId(Long id);
        Funcionario save(Funcionario funcionario);
    //    Funcionario updateFuncionario(Funcionario funcionario);
        List<Funcionario> getAllFuncionarios();
       // void deleteFuncionario(Long id);
        Funcionario aumentoSalario(Long id, BigDecimal valorAumento);

}
