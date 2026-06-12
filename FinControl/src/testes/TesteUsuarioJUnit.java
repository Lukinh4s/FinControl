package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Usuario;

class TesteUsuarioJUnit{

    @Test
    void deveAutenticarUsuarioCorreto() {
        Usuario usuario = new Usuario(
                1,
                "Lucas",
                "lucas@email.com",
                "123456"
        );

        assertTrue(usuario.login("lucas@email.com", "123456"));
    }

    @Test
    void naoDeveAutenticarSenhaIncorreta() {
        Usuario usuario = new Usuario(
                1,
                "Lucas",
                "lucas@email.com",
                "123456"
        );

        assertFalse(usuario.login("lucas@email.com", "senhaerrada"));
    }

    @Test
    void naoDeveAutenticarEmailIncorreto() {
        Usuario usuario = new Usuario(
                1,
                "Lucas",
                "lucas@email.com",
                "123456"
        );

        assertFalse(usuario.login("outro@email.com", "123456"));
    }
}