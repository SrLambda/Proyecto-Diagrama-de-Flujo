package Dibujos.PanelesNoMovibles;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

public class DibujoInicio extends PanelPersonalizado implements MouseWheelListener {
    private double zoomFactor = 1.0;

    public DibujoInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(texto, lista, _contenedor, _restriciones, _ventanaEmergente);
        this.habilitado = false;
        addMouseWheelListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        g2d.scale(zoomFactor, zoomFactor);

        // Calculate dimensions with zoom
        int scaledWidth = (int) (panelWidth / zoomFactor);
        int scaledHeight = (int) (panelHeight / zoomFactor);

        int x1 = (int) ((scaledWidth / 4) + scaledWidth * 0.1);
        int x2 = (int) ((scaledWidth - (scaledWidth / 4)) - scaledWidth * 0.1);
        int y1 = (int) ((scaledHeight / 4) + scaledHeight * 0.15);
        int y2 = (int) ((scaledHeight - (scaledHeight / 4)) - scaledHeight * 0.15);

        int arcWidth = 100;
        int arcHeight = 100;

        g2d.setColor(Color.BLACK);

        int centro_x = scaledWidth / 2;

        g2d.drawRoundRect(x1, y1, x2 - x1, y2 - y1, arcWidth, arcHeight);

        g2d.drawLine(centro_x, y2, centro_x, scaledHeight);

        g2d.setFont(textoFont);

        FontMetrics metrics = g2d.getFontMetrics();
        int x = (scaledWidth - metrics.stringWidth(texto)) / 2;
        int y = ((scaledHeight - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(texto, x, y);

        g2d.dispose();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            zoomFactor *= 1.1;
        } else {
            zoomFactor /= 1.1;
        }
        repaint();
    }
}



