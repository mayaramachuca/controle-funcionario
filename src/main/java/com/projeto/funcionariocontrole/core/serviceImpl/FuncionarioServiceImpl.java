package com.projeto.funcionariocontrole.core.serviceImpl;

import com.projeto.funcionariocontrole.domain.dto.FuncionariosDto;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.funcionariocontrole.domain.repository.FuncionarioRepository;
import com.projeto.funcionariocontrole.domain.service.FuncionarioService;

import java.util.List;
import java.util.Optional;

@Service("funcionarioService")
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override

    public FuncionariosDto buscarPeloCpf(String cpf) {
        Optional<Funcionario> resultado = funcionarioRepository.findByCpf(cpf);
        FuncionariosDto dto = new FuncionariosDto(resultado.get());
        return dto;
    }

        @Override
        public List<Funcionario> getAllFuncionarios() {

        return funcionarioRepository.findAll();
        }

        @Override
        public void deleteAllFuncionarios() {

        funcionarioRepository.deleteAll();
        }

        @Override
        public void deleteFuncionarioByCpf(String cpf) {

        funcionarioRepository.deleteByCpf(cpf);
        }

        @Override
        public void updateFuncionarioByCpf(String cpf,Funcionario funcionario) {
            funcionarioRepository.save(funcionario);
        }

        @Override
        public void insertFuncionario(Funcionario funcionario) {
            funcionarioRepository.save(funcionario);
        }
}

