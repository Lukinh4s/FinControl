package view;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PainelGrafico extends JPanel {

    private double receitas;
    private double despesas;

    public void atualizarValores(double receitas, double despesas) {
        this.receitas = receitas;
        this.despesas = despesas;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double maior = Math.max(receitas, despesas);

        if (maior <= 0) {
            g.drawString("Sem dados para exibir", 20, 30);
            return;
        }

        int alturaReceita = (int) ((receitas / maior) * 150);
        int alturaDespesa = (int) ((despesas / maior) * 150);

        g.drawString("Receitas", 60, 190);
        g.fillRect(70, 170 - alturaReceita, 60, alturaReceita);

        g.drawString("Despesas", 190, 190);
        g.fillRect(200, 170 - alturaDespesa, 60, alturaDespesa);

        g.drawString("R$ " + receitas, 60, 170 - alturaReceita - 10);
        g.drawString("R$ " + despesas, 190, 170 - alturaDespesa - 10);
    }
}