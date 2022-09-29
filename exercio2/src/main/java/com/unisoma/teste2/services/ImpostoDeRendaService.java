package com.unisoma.teste2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisoma.teste2.DTO.ImpostoDeRendaDTO;
import com.unisoma.teste2.Util.SalarioDoFuncionario;


@Service
public class ImpostoDeRendaService {

    @Autowired
    private SalarioDoFuncionario salariodoFuncionario;

    public  ImpostoDeRendaDTO calcularImposto(String CPF) {
        try {
            return new ImpostoDeRendaDTO(CPF, salariodoFuncionario.getSalario(CPF));
        } catch (Error e) {
            throw new Error("Falhar ao calcular o imposto, Motivo: " + e.getMessage());
        }

    }

}
