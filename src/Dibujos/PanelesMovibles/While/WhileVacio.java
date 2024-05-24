package Dibujos.PanelesMovibles.While;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WhileVacio extends PanelPersonalizado {

    public WhileVacio(String _texto, List<PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones) {
        super(_texto, lista, _contenedor,_restriciones);
        setPreferredSize(new Dimension(200, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        int ancho = this.getWidth();
        int alto = this.getHeight();

        g.drawLine(ancho/2,0,ancho/2,alto);
    }

    public void ajustarSize(int altura)
    {

        Dimension size = new Dimension(200,altura);

        this.setPreferredSize(size);

    }
}

