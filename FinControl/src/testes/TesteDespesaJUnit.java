package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import enums.StatusPagamento;
import enums.TipoConta;
import model.Categoria;
import model.Conta;
import model.Despesa;
import model.Usuario;

class TesteDespesaJUnit {

    @Test
    void deveCalcularValorDespesaNegativo() {
        Usuario usuario = new Usuario(1, "Lucas", "lucas@email.com", "123");
        Conta conta = new Conta(1, "Nubank", 1000, TipoConta.CONTA_BANCARIA);
        Categoria categoria = new Categoria(1, "Mercado");

        Despesa despesa = new Despesa(
                1,
                "Compra mercado",
                250.00,
                new Date(System.currentTimeMillis()),
                usuario,
                conta,
                categoria,
                StatusPagamento.PAGO
        );

        assertEquals(-250.00, despesa.calcularValor());
    }

    @Test
    void deveGuardarStatusPagamento() {
        Usuario usuario = new Usuario(1, "Lucas", "lucas@email.com", "123");
        Conta conta = new Conta(1, "Nubank", 1000, TipoConta.CONTA_BANCARIA);
        Categoria categoria = new Categoria(1, "Mercado");

        Despesa despesa = new Despesa(
                1,
                "Compra mercado",
                250.00,
                new Date(System.currentTimeMillis()),
                usuario,
                conta,
                categoria,
                StatusPagamento.PENDENTE
        );

        assertEquals(StatusPagamento.PENDENTE, despesa.getStatusPagamento());
    }
}