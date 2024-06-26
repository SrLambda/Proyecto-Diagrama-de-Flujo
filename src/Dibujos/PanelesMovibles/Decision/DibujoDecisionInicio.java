package Dibujos.PanelesMovibles.Decision;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DibujoDecisionInicio extends PanelPersonalizado {
    private double zoomFactor = 1.0;

    public DibujoDecisionInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(texto, lista, _contenedor, _restriciones, _ventanaEmergente);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto);
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

        int widthTx = this.anchoAlto[0];
        int heightTx = this.anchoAlto[1];

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int centro_x = panelWidth / 2;
        int centro_y = panelHeight / 2;

        int cuarto_x = panelWidth / 4;

        double anguloY = Math.PI / 6;
        double anguloX = Math.PI - anguloY;

        double z1 = Math.sqrt((widthTx * widthTx) / (2 - (2 * Math.cos(anguloX))));
        double z2 = Math.sqrt((heightTx * heightTx) / (2 - (2 * Math.cos(anguloY))));

        double z = z1 + z2;

        int deltX = (int) (z * Math.cos(anguloY / 2));
        int deltY = (int) (z * Math.sin(anguloY / 2));

        // Coordenadas del rombo
        int x1 = centro_x - deltX;
        int x2 = centro_x + deltX;
        int y1 = centro_y - deltY;
        int y2 = centro_y + deltY;

        // Dibujar las líneas que forman el rombo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1, centro_y, centro_x, y1);
        g2d.drawLine(x2, centro_y, centro_x, y1);
        g2d.drawLine(x1, centro_y, centro_x, y2);
        g2d.drawLine(x2, centro_y, centro_x, y2);

        // Dibujar flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x, 0, centro_x, y1);

        // fuente con el tamaño especificado
        g2d.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(texto, x, y);
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        revalidate();
        repaint();
    }
}
