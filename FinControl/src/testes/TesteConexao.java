package testes;

import dao.BDconexao;

public class TesteConexao {

    public static void main(String[] args) {

        BDconexao bd = new BDconexao();

        if (bd.connect()) {
            System.out.println("Conexão realizada com sucesso!");
            bd.close();
        } else {
            System.out.println("Erro ao conectar com o banco.");
        }
    }
}