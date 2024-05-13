package Dibujos.PanelesMovibles.DoWhile;

import Dibujos.PanelPersonalizado;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DibujoDoWhileInicio extends PanelPersonalizado {
    private DibujoDoWhileInterno interno;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);

    public DibujoDoWhileInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoDoWhileInterno _interno) {
        super(texto, lista, _contenedor);
        this.interno = _interno;
        setPreferredSize(new Dimension(200, 200));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic para editar el texto
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto); // Actualizar el texto de la figura
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int centro_x = panelWidth / 2;     // Centro horizontal
        int centro_y = panelHeight / 2;    // Centro vertical

        int cuarto = panelWidth / 4;

        // Dibujar el rectángulo del inicio
        /*g.setColor(Color.BLACK);
        g.drawRect(cuarto, cuarto, panelWidth / 2, panelHeight / 2);

        // Dibujar el texto centrado
        g.setFont(textoFont);
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);*/
    }
}


