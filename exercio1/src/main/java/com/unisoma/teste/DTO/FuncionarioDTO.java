package com.unisoma.teste.DTO;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unisoma.teste.entities.Funcionario;
import com.unisoma.teste.util.Conversor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @CPF
    private String CPF;
    @NotNull
    private Date dataDeNascimento;
    @NotBlank
    private String telefone;
    @NotBlank
    private String endereco;
    @NotNull
    private Float salario;

    public FuncionarioDTO(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.CPF = funcionario.getCpf();
        this.dataDeNascimento = funcionario.getDataDeNascimento();
        this.telefone = funcionario.getTelefone();
        this.endereco = funcionario.getEndereco();
        this.salario = (float) (Math.floor(funcionario.getSalario() * 100) / 100);
    }

    public FuncionarioDTO(String nome, String cpf, Date dataDeNascimento, String telefone, String endereco,
            Float salario) {
        this.nome = nome;
        this.CPF = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.salario = (float) (Math.floor(salario * 100) / 100);
    }

    public Funcionario toEntity() {
        return Conversor.converter(this, Funcionario.class);
    }
}
