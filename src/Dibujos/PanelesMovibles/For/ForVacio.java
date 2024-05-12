package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class ForVacio extends PanelPersonalizado {

    public ForVacio(String _texto, List<PanelPersonalizado> _lista, JPanel _contenedor){
        super(_texto, _lista, _contenedor);
        setPreferredSize(new Dimension(200, 400));
    }

    @Override
    protected void printComponent(Graphics g) {
        super.printComponent(g);
        g.setColor(Color.BLACK);

        int ancho = this.getWidth();
        int alto = this.getHeight();

        g.drawLine(ancho/2,0,ancho/2,alto);

    }
}
