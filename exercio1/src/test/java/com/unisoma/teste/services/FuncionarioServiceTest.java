package com.unisoma.teste.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.unisoma.teste.AplicationConfigTest;
import com.unisoma.teste.DTO.FuncionarioDTO;
import com.unisoma.teste.entities.Funcionario;
import com.unisoma.teste.repositories.FuncionarioRepository;
import com.unisoma.teste.util.ValidadorDeCPF;
import com.unisoma.teste.util.ValidadorDeTelefone;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@DisplayName("Funcionario Service Test")
public class FuncionarioServiceTest extends AplicationConfigTest {

        @MockBean
        FuncionarioRepository funcionarioRepository;
        
        @MockBean
        ValidadorDeCPF validadorDeCPF;

        @MockBean
        ValidadorDeTelefone validadorDeTelefone;

        @Autowired
        FuncionarioService funcionarioService;

        Date data = new Date();
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO("Teste save", "780.318.535-50", data,
                        "(01)12345-6789",
                        "rua feliz, 22", (float) 400.00);

        @Test
        @DisplayName("deve salvar um funcionario")
        public void deveSalvarUmFuncionario() {

                Mockito.when(validadorDeCPF.execute(ArgumentMatchers.anyString())).thenReturn(true);
                Mockito.when(validadorDeTelefone.execute(ArgumentMatchers.anyString())).thenReturn(true);
                Mockito.when(funcionarioRepository.save(ArgumentMatchers.any(Funcionario.class)))
                                .thenReturn(funcionarioDTO.toEntity());

                FuncionarioDTO result = funcionarioService.save(funcionarioDTO);
                Mockito.verify(funcionarioRepository, Mockito.times(1)).save(ArgumentMatchers.any(Funcionario.class));
                Assertions.assertEquals(funcionarioDTO.getCPF(), result.getCPF());
        }

        @Test
        @DisplayName("Deve Lancar Uma Exercao De CPF Invalido")
        public void deveLancarUmaExercaoDeCPFInvalido() {
                Mockito.when(validadorDeCPF.execute(ArgumentMatchers.anyString())).thenReturn(false);
                Mockito.when(validadorDeTelefone.execute(ArgumentMatchers.anyString())).thenReturn(true);
                FuncionarioDTO funcionarioDTOTeste = funcionarioDTO;
                funcionarioDTOTeste.setCPF("123456");
                Assertions.assertThrows(Error.class, () -> funcionarioService.save(funcionarioDTOTeste),
                                "falha ao salvar. MOTIVO: CPF Invalido");
        }

        @Test
        @DisplayName("Deve Lancar Uma Exercao De Telefone Invalido")
        public void deveLancarUmaExercaoDeTelefoneInvalido() {
                Mockito.when(validadorDeCPF.execute(ArgumentMatchers.anyString())).thenReturn(true);
                Mockito.when(validadorDeTelefone.execute(ArgumentMatchers.anyString())).thenReturn(false);
                FuncionarioDTO funcionarioDTOTeste = funcionarioDTO;
                funcionarioDTOTeste.setTelefone("123456");
                Assertions.assertThrows(Error.class, () -> funcionarioService.save(funcionarioDTOTeste),
                                "falha ao salvar. MOTIVO: Telefone Invalido");
        }

        @Test
        @DisplayName("Deve Lancar Uma Exercao De Salario Invalido")
        public void deveLancarUmaExercaoDeSalarioInvalido() {
                Mockito.when(validadorDeCPF.execute(ArgumentMatchers.anyString())).thenReturn(false);
                Mockito.when(validadorDeTelefone.execute(ArgumentMatchers.anyString())).thenReturn(false);
                FuncionarioDTO funcionarioDTOTeste = funcionarioDTO;
                funcionarioDTOTeste.setSalario((float)-12.10);
                Assertions.assertThrows(Error.class, () -> funcionarioService.save(funcionarioDTOTeste),
                                "falha ao salvar. MOTIVO: Salário não pode ser menor que zero");
        }

        @Test
        @DisplayName("Deve Lancar Uma Exercao De CPF Ja Existente")
        public void deveLancarUmaExercaoDeCPFJaExistente() {
                Mockito.when(validadorDeCPF.execute(ArgumentMatchers.anyString())).thenReturn(true);
                Mockito.when(validadorDeTelefone.execute(ArgumentMatchers.anyString())).thenReturn(true);
                Mockito.when(funcionarioRepository.findByCpf(ArgumentMatchers.anyString()))
                                .thenReturn(funcionarioDTO.toEntity());
                Mockito.verify(funcionarioRepository, Mockito.never()).findByCpf(ArgumentMatchers.anyString());
                Assertions.assertThrows(Error.class, () -> funcionarioService.save(funcionarioDTO),
                                "falha ao salvar. MOTIVO: CPF já existente");
        }

        @Test
        @DisplayName("deve fazer update no funcionario")
        public void deveFazerUpdateNoFuncionario() {
                String cpf = funcionarioDTO.getCPF();

                FuncionarioDTO funcionarioDTO2 = new FuncionarioDTO("Teste ", "780.318.535-50", data, "(01)12345-6789",
                                "rua 22", (float) 400.00);

                // Funcionario Funcionario = Mockito.mock(Funcionario.class);
                Mockito.when(validadorDeCPF.execute(ArgumentMatchers.anyString())).thenReturn(true);
                Mockito.when(validadorDeTelefone.execute(ArgumentMatchers.anyString())).thenReturn(true);
                Mockito.when(funcionarioRepository.save(ArgumentMatchers.any(Funcionario.class)))
                                .thenReturn(funcionarioDTO2.toEntity());
                Mockito.when(funcionarioRepository.findByCpf(ArgumentMatchers.anyString()))
                                .thenReturn(funcionarioDTO.toEntity());
                FuncionarioDTO result = funcionarioService.update(funcionarioDTO, cpf);
                Mockito.verify(funcionarioRepository, Mockito.times(1)).save(ArgumentMatchers.any(Funcionario.class));
                Mockito.verify(funcionarioRepository, Mockito.times(1)).findByCpf(ArgumentMatchers.anyString());
                Assertions.assertEquals(funcionarioDTO.getCPF(), result.getCPF());
                Assertions.assertNotEquals(funcionarioDTO.getEndereco(), result.getEndereco());
                Assertions.assertNotEquals(funcionarioDTO.getNome(), result.getNome());
        }

        @Test
        @DisplayName("Deve Lancar Uma Exercao De CPF Invalido No Update")
        public void deveLancarUmaExercaoDeCPFInvalidoNoUpdate() {
                String cpf = "123456";
                Mockito.when(validadorDeCPF.execute(ArgumentMatchers.anyString())).thenReturn(false);
                Mockito.when(validadorDeTelefone.execute(ArgumentMatchers.anyString())).thenReturn(true);
                Assertions.assertThrows(Error.class, () -> funcionarioService.update(funcionarioDTO, cpf),
                                "falha ao salvar. MOTIVO: CPF Invalido");
        }

        @Test
        @DisplayName("Deve Lancar Uma Exercao De Telefone Invalido No Update")
        public void deveLancarUmaExercaoDeTelefoneInvalidoNoUpdate() {
                String cpf = funcionarioDTO.getCPF();
                FuncionarioDTO funcionarioDTOTeste = funcionarioDTO;
                funcionarioDTOTeste.setTelefone("123456");
                Mockito.when(validadorDeCPF.execute(ArgumentMatchers.anyString())).thenReturn(true);
                Mockito.when(validadorDeTelefone.execute(ArgumentMatchers.anyString())).thenReturn(false);
                Assertions.assertThrows(Error.class, () -> funcionarioService.update(funcionarioDTOTeste, cpf),
                                "falha ao salvar. MOTIVO: Telefone Invalido");
        }

        @Test
        @DisplayName("Deve Lancar Uma Exercao De Salario Invalido No Update")
        public void deveLancarUmaExercaoDeSalarioInvalidoNoUpdate() {
                String cpf = funcionarioDTO.getCPF();
                FuncionarioDTO funcionarioDTOTeste = funcionarioDTO;
                funcionarioDTOTeste.setSalario((float) -22.10);
                Mockito.when(validadorDeCPF.execute(ArgumentMatchers.anyString())).thenReturn(true);
                Mockito.when(validadorDeTelefone.execute(ArgumentMatchers.anyString())).thenReturn(false);
                Assertions.assertThrows(Error.class, () -> funcionarioService.update(funcionarioDTOTeste, cpf),
                                "falha ao salvar. MOTIVO: Salário não pode ser menor que zero");
        }

        @Test
        @DisplayName("Deve Exibir Todos os Funcionarios")
        public void deveExibirTodosFuncionarios() {
                List<Funcionario> listFuncionarios = new ArrayList<Funcionario>();
                listFuncionarios.add(funcionarioDTO.toEntity());
                Mockito.when(funcionarioRepository.findAll()).thenReturn(listFuncionarios);
                Mockito.verify(funcionarioRepository, Mockito.never()).findAll();
                Assertions.assertNotEquals(funcionarioService.findAll(), null);
        }

        @Test
        @DisplayName("Deve Falhar ao Exibir Todos os Funcionarios")
        public void deveFalharAoExibirTodosFuncionarios() {
                Mockito.when(funcionarioRepository.findAll()).thenReturn(null);
                Mockito.verify(funcionarioRepository, Mockito.never()).findAll();
                Assertions.assertThrows(Error.class, () -> funcionarioService.findAll(),
                                "falha ao fazer FindAll. MOTIVO: null");
        }

        @Test
        @DisplayName("Deve Exibir Funcionario Pelo CPF")
        public void deveExibirFuncionariosPeloCPF() {
                Mockito.when(funcionarioRepository.findByCpf(ArgumentMatchers.anyString()))
                                .thenReturn(funcionarioDTO.toEntity());
                Mockito.verify(funcionarioRepository, Mockito.never()).findByCpf(ArgumentMatchers.anyString());
                Assertions.assertNotEquals(funcionarioService.findByCpf(funcionarioDTO.getCPF()), null);
                Assertions.assertEquals((funcionarioService.findByCpf(funcionarioDTO.getCPF()).getCPF()), funcionarioDTO.getCPF());
        }

        @Test
        @DisplayName("Deve Falhar ao Exibir Funcionario Pelo CPF")
        public void deveFalharAoExibirFuncionarioPeloCPF() {
                Assertions.assertThrows(Error.class, () -> funcionarioService.findByCpf(null),
                                "falha ao pegar CPF. MOTIVO: null");
        }

        @Test
        @DisplayName("Deve Deletar Funcionario Pelo CPF")
        public void deveDeletarFuncionariosPeloCPF() {
                Mockito.when(funcionarioRepository.findByCpf(ArgumentMatchers.anyString()))
                                .thenReturn(funcionarioDTO.toEntity());
                Mockito.verify(funcionarioRepository, Mockito.never()).findByCpf(ArgumentMatchers.anyString());
                Assertions.assertNotEquals(funcionarioService.delete(funcionarioDTO.getCPF()), null);
                Assertions.assertEquals((funcionarioService.delete(funcionarioDTO.getCPF())),
                                "Funcionario deletado com sucesso, CPF: " + funcionarioDTO.getCPF());
        }

        @Test
        @DisplayName("Deve Falhar ao Deletar Funcionario Pelo CPF")
        public void deveFalharAoDeletarFuncionarioPeloCPF() {
                Assertions.assertThrows(Error.class, () -> funcionarioService.findByCpf(null),
                                "falha ao pegar CPF. MOTIVO: null");
        }
}
