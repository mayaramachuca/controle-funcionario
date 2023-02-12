package com.projeto.funcionariocontrole.app.controller;

import com.projeto.funcionariocontrole.domain.dto.FuncionariosDto;
import com.projeto.funcionariocontrole.domain.constant.StatusFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.projeto.funcionariocontrole.core.serviceImpl.FuncionarioServiceImpl;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioServiceImpl serviceImp;

//    @GetMapping(value = "/cpf/{cpf}")
//    public String String(){
//        return "Teste";
//    }

    @PostMapping()
    public String cadastrarFuncionario(@RequestParam String cpf) {

        FuncionariosDto dto = this.serviceImp.buscarPeloCpf(cpf);

        if (dto != null) {


            return "Funcionário é cadastrado";
        }
//        FuncionariosDto newFuncionario = new FuncionariosDto();
//        newFuncionario.setStatus(StatusFuncionario.ATIVO);
//        return newFuncionario;
        return "cadastrado com sucesso";
    }
}
