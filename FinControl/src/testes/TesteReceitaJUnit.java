package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import enums.TipoConta;
import model.Categoria;
import model.Conta;
import model.Receita;
import model.Usuario;

class TesteReceitaJUnit{

    @Test
    void deveCalcularValorReceitaPositivo() {
        Usuario usuario = new Usuario(1, "Lucas", "lucas@email.com", "123");
        Conta conta = new Conta(1, "Nubank", 0, TipoConta.CONTA_BANCARIA);
        Categoria categoria = new Categoria(1, "Salário");

        Receita receita = new Receita(
                1,
                "Salário Junho",
                3000.00,
                new Date(System.currentTimeMillis()),
                usuario,
                conta,
                categoria,
                "Empresa"
        );

        assertEquals(3000.00, receita.calcularValor());
    }

    @Test
    void deveGuardarFonteReceita() {
        Usuario usuario = new Usuario(1, "Lucas", "lucas@email.com", "123");
        Conta conta = new Conta(1, "Nubank", 0, TipoConta.CONTA_BANCARIA);
        Categoria categoria = new Categoria(1, "Salário");

        Receita receita = new Receita(
                1,
                "Salário",
                3000.00,
                new Date(System.currentTimeMillis()),
                usuario,
                conta,
                categoria,
                "Empresa"
        );

        assertEquals("Empresa", receita.getFonte());
    }
}