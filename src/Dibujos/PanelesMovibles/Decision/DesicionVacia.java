package Dibujos.PanelesMovibles.Decision;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DesicionVacia extends PanelPersonalizado {

    public DesicionVacia(String _texto, List<PanelPersonalizado> lista, JPanel _contenedor) {
        super(_texto, lista, _contenedor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        int ancho = this.getWidth();
        int alto = this.getHeight();

        g.drawLine(ancho/2,0,ancho/2,alto);
    }
}
