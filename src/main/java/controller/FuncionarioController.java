package controller;

import entities.Funcionarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.FuncionarioRepository;
import service.FuncionarioService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {


    @Autowired
    private FuncionarioRepository repository;

    @GetMapping
    public List<Funcionarios> listarFuncionarios(){
        return repository.findAll();
    }
}
