package com.projeto.funcionariocontrole.domain.repository;

import com.projeto.funcionariocontrole.domain.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento,String> {
}
