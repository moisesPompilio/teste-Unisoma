package com.unisoma.teste.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisoma.teste.DTO.FuncionarioDTO;
import com.unisoma.teste.DTO.ReajusteSalarioDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ReajusteSalarioService {
    @Autowired
    private FuncionarioService funcionarioService;

    public ReajusteSalarioDTO reajusteSalario(String CPF) {
        try {
            FuncionarioDTO funcionarioDTO = funcionarioService.findByCpf(CPF);
            ReajusteSalarioDTO responseReajusteSalarioDTO = new ReajusteSalarioDTO();
            responseReajusteSalarioDTO.setCPF(funcionarioDTO.getCPF());
            responseReajusteSalarioDTO.setNovo_Salario(funcionarioDTO.getSalario());
            responseReajusteSalarioDTO.reajusteSalario();
            funcionarioDTO.setSalario(responseReajusteSalarioDTO.getNovo_Salario());
            funcionarioService.update(funcionarioDTO, funcionarioDTO.getCPF());
            return responseReajusteSalarioDTO;
        } catch (Error e) {
            throw new Error(e.getMessage());
        }
    }
}
