package view;

import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;

public class PainelGraficoCategorias extends JPanel {

    private Map<String, Double> dados =
            new LinkedHashMap<>();

    public void atualizarDados(
            Map<String, Double> dados
    ) {
        this.dados = dados;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (dados == null || dados.isEmpty()) {
            g.drawString(
                    "Sem dados para exibir",
                    20,
                    30
            );
            return;
        }

        double maior = 0;

        for (Double valor : dados.values()) {
            if (Math.abs(valor) > maior) {
                maior = Math.abs(valor);
            }
        }

        if (maior == 0) {
            g.drawString(
                    "Sem movimentações nas categorias",
                    20,
                    30
            );
            return;
        }

        int x = 40;
        int larguraBarra = 40;
        int base = 180;

        for (String categoria : dados.keySet()) {

            double valor = Math.abs(
                    dados.get(categoria)
            );

            int altura =
                    (int) ((valor / maior) * 130);

            g.fillRect(
                    x,
                    base - altura,
                    larguraBarra,
                    altura
            );

            g.drawString(
                    categoria.length() > 8
                            ? categoria.substring(0, 8)
                            : categoria,
                    x - 5,
                    base + 20
            );

            g.drawString(
                    "R$ " + valor,
                    x - 5,
                    base - altura - 5
            );

            x += 90;
        }
    }
}