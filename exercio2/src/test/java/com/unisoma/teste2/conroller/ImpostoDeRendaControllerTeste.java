package com.unisoma.teste2.conroller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.unisoma.teste2.AplicationConfigTest;
import com.unisoma.teste2.DTO.ImpostoDeRendaDTO;
import com.unisoma.teste2.services.ImpostoDeRendaService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImpostoDeRendaControllerTeste extends AplicationConfigTest {
    @MockBean
    ImpostoDeRendaService service;

    @Autowired
    private MockMvc mockMvc;

    String PATH = "/impostoDeRenda";

    ImpostoDeRendaDTO impostoDeRendaDTO = new ImpostoDeRendaDTO("503.588.732-40", (float)3002.00);

    @Test
    @DisplayName("Deve Fazer Calcular O Imposto De Renda")
    public void deveFazerCalcularOImpostoDeRenda() throws Exception {
        Mockito.when(service.calcularImposto(ArgumentMatchers.anyString()))
                .thenReturn(impostoDeRendaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + impostoDeRendaDTO.getCPF())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).calcularImposto(anyString());
    }

    @Test
    @DisplayName("Deve Falhar Ao Fazer Calcular O Imposto De Renda")
    public void deveFalharAoCalcularOImpostoDeRenda() throws Exception {
        Mockito.when(service.calcularImposto(ArgumentMatchers.anyString()))
                .thenThrow(new Error());

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + impostoDeRendaDTO.getCPF())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(service, times(1)).calcularImposto(anyString());
    }
}
