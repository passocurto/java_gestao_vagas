package br.com.passocurto.gestao_vagas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PrimeiroTeste {

    @Test
    public void deve_ser_possivel_dois_numeros() {
        var result = calculate(5, 3);
        assertEquals(result, 8);
        ;
    }

    @Test
    public void validar_valor_incorreto() {
        var result = calculate(5, 3);
        assertNotEquals(result, 10);

    }

    public static int calculate(int num1, int num2) {
        return num1 + num2;
    }
}
