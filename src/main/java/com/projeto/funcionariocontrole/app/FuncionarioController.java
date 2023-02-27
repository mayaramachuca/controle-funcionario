package com.projeto.funcionariocontrole.app;

import com.projeto.funcionariocontrole.app.dto.request.FuncionarioRequest;
import com.projeto.funcionariocontrole.app.dto.response.FuncionarioResponse;
import com.projeto.funcionariocontrole.core.mapper.FuncionarioMapper;
import com.projeto.funcionariocontrole.domain.entities.Funcionario;
import com.projeto.funcionariocontrole.domain.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @Autowired
    private FuncionarioMapper funcionarioMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioResponse createFuncionario(@Valid @RequestBody FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = service.save(funcionarioMapper.toEntity(funcionarioRequest));
        return funcionarioMapper.toDto(funcionario);
    }

    @PutMapping(value = "/aumento/{id}")
    public FuncionarioResponse aumentoSalario(@PathVariable Long id, @RequestBody BigDecimal valorAumento) {
        Funcionario funcionario = service.aumentoSalario(id, valorAumento);
        return funcionarioMapper.toDto(funcionario);
    }



    @GetMapping(value = "/todos")
    public List<FuncionarioResponse> listarFuncionarios() {
        return funcionarioMapper.toListDto(service.getAllFuncionarios());
    }


    @GetMapping(value = "/funcionarioid/{id}")
    public Optional<FuncionarioResponse> getFuncionarioId(@PathVariable("id") Long id) {
       Optional<Funcionario> funcionario = service.getFuncionarioId(id);
       var funcionarioResponse = funcionarioMapper.toDto(funcionario.get());
       return Optional.of(funcionarioResponse);
    }

}

