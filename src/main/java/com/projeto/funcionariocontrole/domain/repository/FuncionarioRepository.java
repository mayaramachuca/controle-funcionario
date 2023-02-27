package com.projeto.funcionariocontrole.domain.repository;

import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    Optional<Funcionario> findByCpf(String cpf);

}
