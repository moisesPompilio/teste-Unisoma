package com.unisoma.teste.util;

import java.util.InputMismatchException;

public class ValidadorDeCPF {

    public boolean execute(String cpf) {
        boolean result = validadorDeFormatoAndInformacao(cpf);
        if (!result) {
            return false;
        }
        cpf = formatarcpf(cpf);
        // codigo retiro do site
        // https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097,
        // exeto as funcoes usadas acima
        // considera-se erro cpf's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11)) {
            return false;
        }
        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do cpf em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    private boolean validadorDeFormatoAndInformacao(String cpf) {
        boolean result = true;
        int contadorDeCaracterEspecial = 0;
        int contadorDeNumero = 0;

        if (cpf.length() == 14 && cpf.charAt(3) == '.' && cpf.charAt(7) == '.' && cpf.charAt(11) == '-') {
            for (char c : cpf.toCharArray()) {
                if (Character.isLetter(c)) {
                    result = false;
                    break;
                } else if (Character.isDigit(c)) {
                    contadorDeNumero++;
                } else {
                    contadorDeCaracterEspecial++;
                }
            }
            if (contadorDeNumero != 11 && contadorDeCaracterEspecial != 3) {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }

    private String formatarcpf(String cpf) {
        String cpfFormatado = cpf.replace(".", "");
        cpfFormatado = cpfFormatado.replace("-", "");
        return cpfFormatado;
    }
}
