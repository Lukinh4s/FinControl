package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import enums.TipoConta;
import model.Conta;

class TesteContaJUnit {

    @Test
    void deveAdicionarSaldo() {
        Conta conta = new Conta(1, "Nubank", 1000.00, TipoConta.CONTA_BANCARIA);

        conta.adicionarSaldo(500.00);

        assertEquals(1500.00, conta.getSaldo());
    }

    @Test
    void deveRemoverSaldo() {
        Conta conta = new Conta(1, "Nubank", 1000.00, TipoConta.CONTA_BANCARIA);

        conta.removerSaldo(300.00);

        assertEquals(700.00, conta.getSaldo());
    }

    @Test
    void naoDeveAdicionarValorNegativo() {
        Conta conta = new Conta(1, "Nubank", 1000.00, TipoConta.CONTA_BANCARIA);

        assertThrows(
                IllegalArgumentException.class,
                () -> conta.adicionarSaldo(-100.00)
        );
    }

    @Test
    void naoDeveRemoverValorMaiorQueSaldo() {
        Conta conta = new Conta(1, "Nubank", 100.00, TipoConta.CONTA_BANCARIA);

        assertThrows(
                IllegalArgumentException.class,
                () -> conta.removerSaldo(500.00)
        );
    }
}