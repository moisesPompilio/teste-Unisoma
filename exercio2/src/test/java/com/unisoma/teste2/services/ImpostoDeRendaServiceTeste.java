package com.unisoma.teste2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.unisoma.teste2.AplicationConfigTest;
import com.unisoma.teste2.DTO.ImpostoDeRendaDTO;
import com.unisoma.teste2.Util.SalarioDoFuncionario;

public class ImpostoDeRendaServiceTeste extends AplicationConfigTest {
    @MockBean
    SalarioDoFuncionario salariodoFuncionario;

    @Autowired
    ImpostoDeRendaService service;

    String cpf = "503.588.732-40";

    @Test
    @DisplayName("Deve Retorna O Valor Do Imposto Como Isento")
    public void deveRetornaOValorDoImpostoComoIsento() {

        Mockito.when(salariodoFuncionario.getSalario(ArgumentMatchers.anyString()))
                .thenReturn((float) 2000.00);

        ImpostoDeRendaDTO result = service.calcularImposto(cpf);
        Mockito.verify(salariodoFuncionario, Mockito.times(1)).getSalario(ArgumentMatchers.anyString());
        assertEquals(result.getImposto(), "Isento");
    }

    @Test
    @DisplayName("Deve Retorna O Valor Do Imposto")
    public void deveRetornaOValorDoImposto() {

        Mockito.when(salariodoFuncionario.getSalario(ArgumentMatchers.anyString()))
                .thenReturn((float) 3002.00);

        ImpostoDeRendaDTO result = service.calcularImposto(cpf);
        Mockito.verify(salariodoFuncionario, Mockito.times(1)).getSalario(ArgumentMatchers.anyString());
        assertEquals(result.getImposto(), "R$ 80,36");
    }

    @Test
    @DisplayName("Deve Falhar ao Retorna O Valor Do Imposto")
    public void deveFalharRetornaOValorDoImposto() {

        Mockito.when(salariodoFuncionario.getSalario(ArgumentMatchers.anyString()))
                .thenThrow(new Error());

        assertThrows(Error.class, () -> service.calcularImposto(cpf));
        Mockito.verify(salariodoFuncionario, Mockito.times(1)).getSalario(ArgumentMatchers.anyString());
    }
}
