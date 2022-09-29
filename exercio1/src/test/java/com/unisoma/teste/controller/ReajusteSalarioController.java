package com.unisoma.teste.controller;

import com.unisoma.teste.AplicationConfigTest;
import com.unisoma.teste.DTO.ReajusteSalarioDTO;
import com.unisoma.teste.services.ReajusteSalarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Reajuste Salario Controller Test")
public class ReajusteSalarioController extends AplicationConfigTest {
    @MockBean
    private ReajusteSalarioService service;

    @Autowired
    private MockMvc mockMvc;

    private static final String PATH = "/reajusteSalario";
    ReajusteSalarioDTO reajusteSalarioDTO = new ReajusteSalarioDTO("503.588.732-40", (float) 460.0, (float) 60.0,
            (float) 15.0);

    @Test
    @DisplayName("Deve Fazer Um Rejuste No Salario Do Funcionario")
    public void deveFazerUmRejusteNoSalarioDoFuncionario() throws Exception {
        Mockito.when(service.reajusteSalario(ArgumentMatchers.anyString()))
                .thenReturn(reajusteSalarioDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH + "/" + reajusteSalarioDTO.getCPF())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).reajusteSalario(anyString());
    }

    @Test
    @DisplayName("Deve Falhar Ao Fazer Um Rejuste No Salario Do Funcionario")
    public void deveFalharAoFazerUmRejusteNoSalarioDoFuncionario() throws Exception {
        Mockito.when(service.reajusteSalario(ArgumentMatchers.anyString()))
                .thenThrow(new Error());

        mockMvc.perform(MockMvcRequestBuilders.post(PATH + "/" + reajusteSalarioDTO.getCPF())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(service, times(1)).reajusteSalario(anyString());
    }
}
