package com.projeto.funcionariocontrole.core;

import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import com.projeto.funcionariocontrole.domain.exception.CpfJaRegistradoException;
import com.projeto.funcionariocontrole.domain.exception.FuncionarioNaoEncontradoException;
import com.projeto.funcionariocontrole.domain.exception.OrcamentoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.funcionariocontrole.domain.repository.FuncionarioRepository;
import com.projeto.funcionariocontrole.domain.service.FuncionarioService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service("funcionarioService")
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @Override
    public  Optional<Funcionario> getFuncionarioId(Long id){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if(funcionario.isEmpty()){
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado!");
        }
        return funcionario;
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        Optional<Funcionario> getFuncionario = funcionarioRepository.findByCpf(funcionario.getCpf());

        if (getFuncionario.isPresent()) {
            throw new CpfJaRegistradoException("CPF já existe!");
        }

        if (funcionario.getSalario().compareTo(funcionario.getDepartamento().orcamentoDisponivel()) <= 0) {
            funcionario.getDepartamento().addSalario(funcionario.getSalario().setScale(2, RoundingMode.HALF_EVEN));
            funcionario.setStatus(StatusFuncionario.ATIVO);
            return funcionarioRepository.save(funcionario);
        }
        funcionario.setStatus(StatusFuncionario.REJEITADO_FALTA_RECURSO);
        funcionario.setDataRejeicao(LocalDate.now());
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario aumentoSalario(Long id, BigDecimal valorAumento) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário não cadastrado"));

        if (valorAumento.compareTo(funcionario.getDepartamento().orcamentoDisponivel()) <= 0) {
            var novoSalario = funcionario.getSalario().add(valorAumento);
            funcionario.setSalario(novoSalario);
            funcionario.getDepartamento().addSalario(valorAumento);
            return funcionarioRepository.save(funcionario);
        }
        throw new OrcamentoInsuficienteException("Orçamento insuficiente");
    }

    /* @Override
    public void deleteFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(()-> new FuncionarioNaoEncontradoException("Funcionário não cadastrado"));
        funcionarioRepository.deleteById(funcionario.getId());
    }*/
}

