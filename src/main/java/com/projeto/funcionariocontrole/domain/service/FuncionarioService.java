package com.projeto.funcionariocontrole.domain.service;

import com.projeto.funcionariocontrole.domain.dto.FuncionariosDto;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;

import java.util.List;


public interface FuncionarioService {
        FuncionariosDto buscarPeloCpf(String cpf);

        List<Funcionario> getAllFuncionarios();

        void deleteAllFuncionarios();

        void deleteFuncionarioByCpf(String cpf);

        void updateFuncionarioByCpf(String cpf, Funcionario funcionario);
        void insertFuncionario(Funcionario funcionario);

}
