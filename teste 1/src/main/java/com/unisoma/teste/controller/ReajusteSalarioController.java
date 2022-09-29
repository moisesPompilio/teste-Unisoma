package com.unisoma.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisoma.teste.services.ReajusteSalarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/reajusteSalario")
public class ReajusteSalarioController {
    @Autowired
    ReajusteSalarioService service;

    @PostMapping(value = "/{CPF}")
    public ResponseEntity<Object> findById(@PathVariable String CPF) {
        try {
            return ResponseEntity.ok().body(service.reajusteSalario(CPF));
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
