package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import util.FormatadorMoeda;

class TesteFormatoMoedaJUnit {

    @Test
    void deveFormatarMoedaBrasileira() {
        String valor = FormatadorMoeda.formatar(1500.50);

        assertTrue(valor.contains("1.500"));
        assertTrue(valor.contains("50"));
    }
}