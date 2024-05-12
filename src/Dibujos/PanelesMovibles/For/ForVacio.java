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
        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int x1 = (int) (anchoPanel*0.171);
        int x2 = (int) (anchoPanel*0.828);
        int y1 = (int) ((altoPanel / 4)+altoPanel*0.15);
        g.setColor(Color.RED);
        g.drawLine(x1,y1,x1,y1+60);

    }
}
