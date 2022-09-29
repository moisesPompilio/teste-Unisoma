package com.unisoma.teste2.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImpostoDeRendaDTO {
    private String imposto;
    private String CPF;

    public ImpostoDeRendaDTO(String CPF, float salario) {
        this.CPF = CPF;
        this.imposto = formatadorDeImposto(calcularImposto(salario));
    }

    private float calcularImposto(float salario) {
        float imposto = 0;
        float variavelDeCalculo = 0;
        if (salario > 4500) {
            variavelDeCalculo = salario - 4500;
            imposto += variavelDeCalculo * 28 / 100;
            salario = 4500;
        }
        if (salario > 3000) {
            variavelDeCalculo = salario - 3000;
            imposto += variavelDeCalculo * 18 / 100;
            salario = 3000;
        }
        if (salario > 2000) {
            variavelDeCalculo = salario - 2000;
            imposto += variavelDeCalculo * 8 / 100;
            salario = 2000;
        }
        return imposto;
    }

    private String formatadorDeImposto(float imposto) {
        if (imposto == 0) {
            return "Isento";
        } else {
            return ("R$ " + String.format("%.2f", imposto));
        }
    }
}
