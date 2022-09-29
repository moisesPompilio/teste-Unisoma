package com.unisoma.teste.util;

public class ValidadorDeTelefone {
    public boolean execute(String telefone) {
        boolean result = true;
        int contadorDeCaracterEspecial = 0;
        int contadorDeNumero = 0;

        if (telefone.length() == 14 && telefone.charAt(0) == '('
                && telefone.charAt(3) == ')'
                && telefone.charAt(9) == '-') {
            for (char c : telefone.toCharArray()) {
                if (Character.isLetter(c)) {
                    return false;
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
            return false;
        }
        return result;
    }
}
