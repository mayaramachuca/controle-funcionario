package com.projeto.funcionariocontrole.app.controller;

import com.projeto.funcionariocontrole.app.dto.request.FuncionarioRequest;
import com.projeto.funcionariocontrole.app.dto.response.FuncionarioResponse;
import com.projeto.funcionariocontrole.core.mapper.FuncionarioMapper;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.projeto.funcionariocontrole.core.serviceImpl.FuncionarioServiceImpl;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioServiceImpl serviceImp;

    @Autowired
    private FuncionarioMapper funcionarioMapper;

    @PostMapping
    public FuncionarioResponse createFuncionario(@Valid @RequestBody FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = serviceImp.save(funcionarioMapper.toEntity(funcionarioRequest));
        return funcionarioMapper.toDto(funcionario);
    }

    @PutMapping(value = "/aumento/{id}")
    public FuncionarioResponse aumentoSalario(@PathVariable Long id, @RequestBody BigDecimal valorAumento) {
        Funcionario funcionario = serviceImp.aumentoSalario(id, valorAumento);
        return funcionarioMapper.toDto(funcionario);
    }



    @GetMapping(value = "/todos")
    public List<FuncionarioResponse> listarFuncionarios() {
        return funcionarioMapper.toListDto(serviceImp.getAllFuncionarios());
    }


    @GetMapping(value = "/funcionarioid/{id}")
    public Optional<FuncionarioResponse> getFuncionarioId(@PathVariable("id") Long id) {
       Optional<Funcionario> funcionario = serviceImp.getFuncionarioId(id);
       var funcionarioResponse = funcionarioMapper.toDto(funcionario.get());
       return Optional.of(funcionarioResponse);
    }

}

