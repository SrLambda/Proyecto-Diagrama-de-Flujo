package Dibujos.PanelesMovibles.DoWhile;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DoWhileVacio extends PanelPersonalizado {

    public DoWhileVacio(String _texto, List<PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(_texto, lista, _contenedor,_restriciones,_ventanaEmergente);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        int ancho = this.getWidth();
        int alto  = this.getHeight();

        int x;

        if(this.texto == "izquierda")
        {

            x = 100;

        }
        else
        {
            x = ancho / 2;
        }
        g.drawLine(x,0,x ,alto);

    }
}