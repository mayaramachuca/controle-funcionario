package com.projeto.funcionariocontrole.app.controller;

import com.projeto.funcionariocontrole.app.dto.request.DepartamentoRequest;
import com.projeto.funcionariocontrole.app.dto.response.DepartamentoResponse;
import com.projeto.funcionariocontrole.core.mapper.DepartamentoMapper;
import com.projeto.funcionariocontrole.core.serviceImpl.DepartamentoServiceImpl;
import com.projeto.funcionariocontrole.domain.entities.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.projeto.funcionariocontrole.core.mapper.DepartamentoMapper.*;

@RestController
@RequestMapping("departamentos")
public class DepartamentoController {

    @Autowired
    public DepartamentoMapper departamentoMapper;
    @Autowired
    public DepartamentoServiceImpl departamentoImp;

    @PostMapping
    public DepartamentoResponse createDepartamento(@Valid @RequestBody DepartamentoRequest departamentoRequest){
        Departamento departamento =  departamentoImp.save(toEntity(departamentoRequest));
        return departamentoMapper.toDto(departamento);
    }

    @GetMapping(value = "/{id}")
    public DepartamentoResponse getDepartamento(@PathVariable("id")Long id) {
        Departamento departamento = departamentoImp.getDepartamentoId(id);
        var departamentoResponse = departamentoMapper.toDto(departamento);
        return departamentoResponse;
    }

    @GetMapping(value = "/todos")
    public List<DepartamentoResponse> listarDepartamentos() {

        return departamentoMapper.toListDto(departamentoImp.getAllDepartamento());
    }

}
