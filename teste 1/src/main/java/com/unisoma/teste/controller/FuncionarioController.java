package com.unisoma.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisoma.teste.DTO.FuncionarioDTO;
import com.unisoma.teste.services.FuncionarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            return ResponseEntity.ok().body(service.findAll());
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping(value = "/{CPF}")
    public ResponseEntity<Object> findById(@PathVariable String CPF) {
        try {
            return ResponseEntity.ok().body(service.findByCpf(CPF));
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Funcionario Invalido");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(funcionarioDTO));
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{CPF}")
    public ResponseEntity<Object> delete(@PathVariable String CPF) {
        try {
            if(CPF == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF Invalido");
            }
            return ResponseEntity.ok().body(service.delete(CPF));
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{CPF}")
    public ResponseEntity<Object> save(@RequestBody FuncionarioDTO funcionarioDTO, @PathVariable String CPF) {
        try {
            if (funcionarioDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.ok().body(service.update(funcionarioDTO, CPF));
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
