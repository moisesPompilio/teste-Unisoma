package com.unisoma.teste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unisoma.teste.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
   List<Funcionario> findAll();
   Funcionario findByCpf(String cpf);
}
