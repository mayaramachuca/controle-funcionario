package com.projeto.funcionariocontrole.core.serviceImpl;

import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.exception.DepartamentoJaRegistradoException;
import com.projeto.funcionariocontrole.domain.exception.DepartamentoNaoEncontradoException;
import com.projeto.funcionariocontrole.domain.repository.DepartamentoRepository;
import com.projeto.funcionariocontrole.domain.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departamentoService")
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    DepartamentoRepository departamentoRepository;

  /*  @Override
    public Departamento getDepartamentoNome(String nome) {
        Departamento departamento = departamentoRepository.findByNome(nome).orElseThrow(()-> new DepartamentoNaoEncontradoException("Departamento não encontrado!"));

        return departamento;
    }*/

    @Override
    public Departamento getDepartamentoId(Long id) {
        Departamento departamento = departamentoRepository.findById(id).orElseThrow(()-> new DepartamentoNaoEncontradoException("Departamento não enconrado"));
        return departamento;
    }

    @Override
    public List<Departamento> getAllDepartamento() {
        return departamentoRepository.findAll();
    }

    @Override
    public void deleteDepartamentoNome(String nome) {
        Departamento departamento = departamentoRepository.findByNome(nome).get();
        departamentoRepository.deleteById(departamento.getId());
    }

    @Override
    public Departamento save(Departamento departamento) {

       if (departamentoRepository.findByNome(departamento.getNome()).isPresent()){
            throw new DepartamentoJaRegistradoException("Departamento já registrado");
        }
        return departamentoRepository.save(departamento);
    }

 /*   @Override
    public Departamento updateDepartamento(Departamento departamento) {

        Departamento departamento1 = departamentoRepository.findByNome(departamento.getNome()).orElseThrow(()-> new DepartamentoNaoEncontradoException("Departamento não encontrado!"));

        departamento.setTotalSalarios(departamento1.getTotalSalarios());
        departamento.setNome(departamento1.getNome());
        departamento.setBudget(departamento1.getBudget());
        departamento.setDescricao(departamento1.getDescricao());
        departamento.setFuncionarios(departamento1.getFuncionarios());
            return departamentoRepository.save(departamento);
        }*/
}