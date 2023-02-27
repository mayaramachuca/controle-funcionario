package com.projeto.funcionariocontrole.domain.repository;

import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento,Long> {

    Optional<Departamento> findDepartamentoNome(String nome);



}
