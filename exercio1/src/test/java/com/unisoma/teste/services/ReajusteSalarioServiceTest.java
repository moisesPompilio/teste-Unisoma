package com.unisoma.teste.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.unisoma.teste.AplicationConfigTest;
import com.unisoma.teste.DTO.FuncionarioDTO;
import com.unisoma.teste.DTO.ReajusteSalarioDTO;
import com.unisoma.teste.entities.Funcionario;
import com.unisoma.teste.repositories.FuncionarioRepository;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@DisplayName("ReajusteSalario Service Test")
public class ReajusteSalarioServiceTest extends AplicationConfigTest {

    @MockBean
    FuncionarioService funcionarioService;

    @MockBean
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ReajusteSalarioService reajusteSalarioService;

    Date data = new Date();

    FuncionarioDTO funcionarioDTO = new FuncionarioDTO("Teste save", "780.318.535-50", data,
            "(01)12345-6789",
            "rua feliz, 22", (float) 400.00);

    @Test
    @DisplayName("Deve Reajustar Salario Em 15")
    public void deveReajustarSalarioEm15() {
        Funcionario funcionario = Mockito.mock(Funcionario.class);
        Mockito.when(funcionarioService.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(funcionarioDTO);
        Mockito.when(funcionarioRepository.findByCpf(funcionarioDTO.getCPF())).thenReturn(funcionario);

        float salario = 400;
        float expectativa = (float)(Math.floor(400 * 115) / 100);
        funcionarioDTO.setSalario(salario);
        ReajusteSalarioDTO reajusteSalarioDTO = reajusteSalarioService.reajusteSalario(funcionarioDTO.getCPF());

        Mockito.verify(funcionarioService, Mockito.times(1)).findByCpf(ArgumentMatchers.anyString());
        Mockito.verify(funcionarioService, Mockito.times(1)).update(ArgumentMatchers.any(FuncionarioDTO.class), ArgumentMatchers.anyString());
        Assertions.assertEquals(reajusteSalarioDTO.getNovo_Salario(),
                (expectativa));
    }
    @Test
    @DisplayName("Deve Reajustar Salario Em 12")
    public void deveReajustarSalarioEm12() {
        Funcionario funcionario = Mockito.mock(Funcionario.class);
        Mockito.when(funcionarioService.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(funcionarioDTO);
        Mockito.when(funcionarioRepository.findByCpf(funcionarioDTO.getCPF())).thenReturn(funcionario);

        float salario = 545;
        float expectativa = (float)(Math.floor(salario * 112) / 100);
        funcionarioDTO.setSalario(salario);
        ReajusteSalarioDTO reajusteSalarioDTO = reajusteSalarioService.reajusteSalario(funcionarioDTO.getCPF());

        Mockito.verify(funcionarioService, Mockito.times(1)).findByCpf(ArgumentMatchers.anyString());
        Mockito.verify(funcionarioService, Mockito.times(1)).update(ArgumentMatchers.any(FuncionarioDTO.class), ArgumentMatchers.anyString());
        Assertions.assertEquals(reajusteSalarioDTO.getNovo_Salario(),
                (expectativa));
    }
    @Test
    @DisplayName("Deve Reajustar Salario Em 10")
    public void deveReajustarSalarioEm10() {
        Funcionario funcionario = Mockito.mock(Funcionario.class);
        Mockito.when(funcionarioService.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(funcionarioDTO);
        Mockito.when(funcionarioRepository.findByCpf(funcionarioDTO.getCPF())).thenReturn(funcionario);

        float salario = 1186;
        float expectativa = (float)(Math.floor(salario * 110) / 100);
        funcionarioDTO.setSalario(salario);
        ReajusteSalarioDTO reajusteSalarioDTO = reajusteSalarioService.reajusteSalario(funcionarioDTO.getCPF());

        Mockito.verify(funcionarioService, Mockito.times(1)).findByCpf(ArgumentMatchers.anyString());
        Mockito.verify(funcionarioService, Mockito.times(1)).update(ArgumentMatchers.any(FuncionarioDTO.class), ArgumentMatchers.anyString());
        Assertions.assertEquals(reajusteSalarioDTO.getNovo_Salario(),
                (expectativa));
    }
    @Test
    @DisplayName("Deve Reajustar Salario Em 7")
    public void deveReajustarSalarioEm7() {
        Funcionario funcionario = Mockito.mock(Funcionario.class);
        Mockito.when(funcionarioService.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(funcionarioDTO);
        Mockito.when(funcionarioRepository.findByCpf(funcionarioDTO.getCPF())).thenReturn(funcionario);

        float salario = 1367;
        float expectativa = (float)(Math.floor(salario * 107) / 100);
        funcionarioDTO.setSalario(salario);
        ReajusteSalarioDTO reajusteSalarioDTO = reajusteSalarioService.reajusteSalario(funcionarioDTO.getCPF());

        Mockito.verify(funcionarioService, Mockito.times(1)).findByCpf(ArgumentMatchers.anyString());
        Mockito.verify(funcionarioService, Mockito.times(1)).update(ArgumentMatchers.any(FuncionarioDTO.class), ArgumentMatchers.anyString());
        Assertions.assertEquals(reajusteSalarioDTO.getNovo_Salario(),
                (expectativa));
    }
    @Test
    @DisplayName("Deve Reajustar Salario Em 4")
    public void deveReajustarSalarioEm4() {
        Funcionario funcionario = Mockito.mock(Funcionario.class);
        Mockito.when(funcionarioService.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(funcionarioDTO);
        Mockito.when(funcionarioRepository.findByCpf(funcionarioDTO.getCPF())).thenReturn(funcionario);

        float salario = 2123;
        float expectativa = (float)(Math.floor(salario * 104) / 100);
        funcionarioDTO.setSalario(salario);
        ReajusteSalarioDTO reajusteSalarioDTO = reajusteSalarioService.reajusteSalario(funcionarioDTO.getCPF());

        Mockito.verify(funcionarioService, Mockito.times(1)).findByCpf(ArgumentMatchers.anyString());
        Mockito.verify(funcionarioService, Mockito.times(1)).update(ArgumentMatchers.any(FuncionarioDTO.class), ArgumentMatchers.anyString());
        Assertions.assertEquals(reajusteSalarioDTO.getNovo_Salario(),
                (expectativa));
    }
}
