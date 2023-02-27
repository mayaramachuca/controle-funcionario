package com.projeto.funcionariocontrole.domain.repository;

import com.projeto.funcionariocontrole.domain.entities.Departamento;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Long> {
    Optional<Departamento> findByNome(String nome);
}
