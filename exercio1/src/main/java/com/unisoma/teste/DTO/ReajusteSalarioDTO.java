package com.unisoma.teste.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReajusteSalarioDTO {
    private String CPF;
    private float Novo_Salario;
    private float Reajuste_Ganho;
    private float Em_percentual;

    public void reajusteSalario() {
        if (this.Novo_Salario <= 400) {
            executeReajuste(15);
        } else if (this.Novo_Salario <= 800) {
            executeReajuste(12);
        } else if (this.Novo_Salario <= 1200) {
            executeReajuste(10);
        } else if (this.Novo_Salario <= 2000) {
            executeReajuste(7);
        } else if (this.Novo_Salario > 2000.00) {
            executeReajuste(4);
        }
    }

    private void executeReajuste(float percentual) {
        this.Reajuste_Ganho = (float) (Math.floor(this.Novo_Salario * (percentual)) / 100);
        this.Em_percentual = percentual;
        this.Novo_Salario = (float) (Math.floor(this.Novo_Salario * (100 + percentual)) / 100);
    }
}
