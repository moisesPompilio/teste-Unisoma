package com.unisoma.teste2.Util;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SalarioDoFuncionario {
    RestTemplate restTemplate = new RestTemplate();

    String urlAPI = "http://api:8080/api/funcionario/";
    public Float getSalario(String CPF) {
        String url = urlAPI + CPF;
        try {
        Object funcionario = restTemplate.getForObject(url, Object.class);
        float salario = Float.parseFloat(funcionario.toString().split("salario=")[1].split(",")[0].replace("}", ""));
        return salario;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
