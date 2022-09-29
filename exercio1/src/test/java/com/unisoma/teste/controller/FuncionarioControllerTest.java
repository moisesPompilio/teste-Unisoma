package com.unisoma.teste.controller;

import com.unisoma.teste.AplicationConfigTest;
import com.unisoma.teste.DTO.FuncionarioDTO;
import com.unisoma.teste.services.FuncionarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Funcionario Controller Test")
public class FuncionarioControllerTest extends AplicationConfigTest {
        @MockBean
        private FuncionarioService service;

        @Autowired
        private MockMvc mockMvc;

        private static final String PATH = "/funcionario";
        Date data = new Date();
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO("Teste save", "780.318.535-50", data,
                        "(01)12345-6789",
                        "rua feliz, 22", (float) 400.00);

        @Test
        @DisplayName("Deve Salvar Um Funcionario")
        public void deveSalvarUmFuncionario() throws Exception {
                String json = new ObjectMapper().writeValueAsString(funcionarioDTO);

                Mockito.when(service.save(ArgumentMatchers.any(FuncionarioDTO.class)))
                                .thenReturn(funcionarioDTO);

                mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isCreated());
                verify(service, times(1)).save(any(FuncionarioDTO.class));
        }

        @Test
        @DisplayName("Deve Falhar Salvar Um Funcionario")
        public void deveFalharSalvarUmFuncionario() throws Exception {
                String json = new ObjectMapper().writeValueAsString(funcionarioDTO);

                Mockito.when(service.save(ArgumentMatchers.any(FuncionarioDTO.class)))
                                .thenThrow(new Error());

                mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isBadRequest());
                verify(service, times(1)).save(any(FuncionarioDTO.class));
        }

        @Test
        @DisplayName("Deve Fazer Um Update de Funcionario")
        public void deveFazerUmUpdateFuncionario() throws Exception {
                String json = new ObjectMapper().writeValueAsString(funcionarioDTO);

                Mockito.when(service.update(ArgumentMatchers.any(FuncionarioDTO.class), ArgumentMatchers.anyString()))
                                .thenReturn(funcionarioDTO);

                mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/" + funcionarioDTO.getCPF())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isOk());
                verify(service, times(1)).update(any(FuncionarioDTO.class), anyString());
        }

        @Test
        @DisplayName("Deve Falhar Fazer Um Update de Funcionario")
        public void deveFalharFazerUmUpdateFuncionario() throws Exception {
                String json = new ObjectMapper().writeValueAsString(funcionarioDTO);

                Mockito.when(service.update(ArgumentMatchers.any(FuncionarioDTO.class), ArgumentMatchers.anyString()))
                                .thenThrow(new Error());

                mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/" + funcionarioDTO.getCPF())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isBadRequest());
                verify(service, times(1)).update(any(FuncionarioDTO.class), anyString());
        }

        @Test
        @DisplayName("Deve Buscar Um Funcionario Pelo CPF")
        public void deveBuscarUmFuncionarioPeloCPF() throws Exception {
                Mockito.when(service.findByCpf(ArgumentMatchers.anyString()))
                                .thenReturn(funcionarioDTO);

                mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + funcionarioDTO.getCPF())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());
                verify(service, times(1)).findByCpf(anyString());
        }

        @Test
        @DisplayName("Deve Falhar Ao Buscar Um Funcionario Pelo CPF")
        public void deveFalharAoBuscarUmFuncionarioPeloCPF() throws Exception {
                Mockito.when(service.findByCpf(ArgumentMatchers.anyString()))
                                .thenThrow(new Error());

                mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + funcionarioDTO.getCPF())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound());
                verify(service, times(1)).findByCpf(anyString());
        }

        @Test
        @DisplayName("Deve Deletar Um Funcionario Pelo CPF")
        public void deveDeletarUmFuncionarioPeloCPF() throws Exception {
                Mockito.when(service.delete(ArgumentMatchers.anyString()))
                                .thenReturn("Funcionario deletado com sucesso, CPF: " + funcionarioDTO.getCPF());

                mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/" + funcionarioDTO.getCPF())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());
                verify(service, times(1)).delete(anyString());
        }

        @Test
        @DisplayName("Deve Falhar Ao Deletar Um Funcionario Pelo CPF")
        public void deveFalharAoDeletarUmFuncionarioPeloCPF() throws Exception {
                Mockito.when(service.delete(ArgumentMatchers.anyString()))
                                .thenThrow(new Error());

                mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/" + funcionarioDTO.getCPF())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound());
                verify(service, times(1)).delete(anyString());
        }

        @Test
        @DisplayName("Deve Retornar Todos Funcionarios")
        public void deveRetornarTodosFuncionarios() throws Exception {
                List<FuncionarioDTO> list = new ArrayList<FuncionarioDTO>();
                list.add(funcionarioDTO);

                Mockito.when(service.findAll())
                                .thenReturn(list);

                mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());
                verify(service, times(1)).findAll();
        }

        @Test
        @DisplayName("Deve Falhar Ao Deve Retornar Todos Funcionarios")
        public void deveFalharAoRetornarTodosFuncionarios() throws Exception {
                Mockito.when(service.findAll())
                                .thenThrow(new Error());

                mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isBadRequest());
                verify(service, times(1)).findAll();
        }
}
