package com.unisoma.teste.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisoma.teste.DTO.FuncionarioDTO;
import com.unisoma.teste.entities.Funcionario;
import com.unisoma.teste.repositories.FuncionarioRepository;
import com.unisoma.teste.util.ValidadorDeCPF;
import com.unisoma.teste.util.ValidadorDeTelefone;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private ValidadorDeTelefone validadorDeTelefone = new ValidadorDeTelefone();
    private ValidadorDeCPF validadorDeCPF = new ValidadorDeCPF();

    public FuncionarioDTO findByCpf(String cpf) {
        try {
            return (funcionarioRepository.findByCpf(cpf)).toDto();
        } catch (Exception e) {
            throw new Error("falha ao pegar CPF. MOTIVO: " + e.getMessage());
        }
    }

    public List<FuncionarioDTO> findAll() {
        try {
            return funcionarioRepository.findAll().stream()
                    .map(Funcionario::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Error("falha ao fazer FindAll. MOTIVO: " + e.getMessage());
        }

    }

    public FuncionarioDTO save(FuncionarioDTO funcionarioDTO) {
        try {
            if (!validadorDeTelefone.execute(funcionarioDTO.getTelefone())) {
                throw new IllegalArgumentException("Telefone inválido");
            }
            if (!validadorDeCPF.execute(funcionarioDTO.getCPF())) {
                throw new IllegalArgumentException("CPF Invalido");
            }
            if (funcionarioDTO.getSalario() < 0) {
                throw new IllegalArgumentException("Salário não pode ser menor que zero");
            }
            return funcionarioRepository.save(funcionarioDTO.toEntity()).toDto();
        } catch (Exception e) {
            try {
                if (funcionarioRepository.findByCpf(funcionarioDTO.getCPF()) != null) {
                    throw new IllegalArgumentException("falha ao salvar. MOTIVO: CPF já existente");
                }
                throw new Error("falha ao salvar. MOTIVO: " + e.getMessage());
            } catch (Exception exception) {
                throw new Error("falha ao salvar. MOTIVO: " + e.getMessage());
            }
        }
    }

    public String delete(String cpf) {
        try {
            funcionarioRepository.delete(findByCpf(cpf).toEntity());
            return "Funcionario deletado com sucesso, CPF: " + cpf;
        } catch (Error e) {
            throw new Error("falha ao delete funcionario. MOTIVO: " + e.getMessage());
        }
    }

    public FuncionarioDTO update(FuncionarioDTO funcionarioDTO, String cpf) {
        try {
            if (!validadorDeTelefone.execute(funcionarioDTO.getTelefone())) {
                throw new IllegalArgumentException("Telefone inválido");
            }
            if (!validadorDeCPF.execute(cpf)) {
                throw new Error("falha ao salvar. MOTIVO: CPF Invalido");
            }
            if (funcionarioDTO.getSalario() < 0) {
                throw new IllegalArgumentException("Salário não pode ser menor que zero");
            }
            FuncionarioDTO funcionarioDTOBanco = funcionarioRepository.findByCpf(cpf).toDto();
            funcionarioDTO.setCPF(cpf);
            funcionarioDTO.setId(funcionarioDTOBanco.getId());
            FuncionarioDTO result = new FuncionarioDTO(funcionarioRepository.save(funcionarioDTO.toEntity()));
            return result;
        } catch (Exception e) {
            throw new Error("falha ao salvar. MOTIVO: " + e.getMessage());
        }
    }
}
