package com.unisoma.teste2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisoma.teste2.services.ImpostoDeRendaService;

@RestController
@CrossOrigin
@RequestMapping(value = "/impostoDeRenda")
public class ImpostoDeRendaCOntroller {
    
    @Autowired
    private ImpostoDeRendaService service;

    @GetMapping("/{CPF}")
    public ResponseEntity<Object> calcularImposto(@PathVariable String CPF){
        try {
            return ResponseEntity.ok().body(service.calcularImposto(CPF));   
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
