package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Categoria;

class TesteCategoriaJUnit{

    @Test
    void deveCriarCategoria() {
        Categoria categoria = new Categoria(1, "Alimentação");

        assertEquals(1, categoria.getId());
        assertEquals("Alimentação", categoria.getNome());
    }

    @Test
    void deveAlterarNomeCategoria() {
        Categoria categoria = new Categoria(1, "Mercado");

        categoria.setNome("Alimentação");

        assertEquals("Alimentação", categoria.getNome());
    }
}