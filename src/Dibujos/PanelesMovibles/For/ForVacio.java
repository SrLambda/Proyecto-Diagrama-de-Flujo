package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ForVacio extends PanelPersonalizado {

    public ForVacio(String _texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                    VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(_texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        int ancho = this.getWidth();
        int alto = this.getHeight();

        g.drawLine(ancho/2,0,ancho/2,alto);
    }

    public void ajustarSize(int ancho,int altura)
    {

        Dimension size = new Dimension(ancho,altura);

        this.setPreferredSize(size);

    }
}