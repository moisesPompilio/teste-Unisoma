package com.unisoma.teste.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.unisoma.teste.DTO.FuncionarioDTO;
import com.unisoma.teste.util.Conversor;

import lombok.AllArgsConstructor;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String nome;
    @Column(unique = true)
    @NotBlank
    private String cpf;
    @Column
    @NotNull
    private Date dataDeNascimento;
    @Column
    @NotBlank
    private String telefone;
    @Column
    @NotBlank
    private String endereco;
    @Column
    @NotNull
    private Float salario;

    public FuncionarioDTO toDto(){
		return Conversor.converter(this, FuncionarioDTO.class);
	}
}
