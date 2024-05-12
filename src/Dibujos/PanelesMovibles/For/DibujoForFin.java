package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DibujoForFin extends PanelPersonalizado {

    public DibujoForFin(String _texto, List<PanelPersonalizado> _lista, JPanel _contenedor){
        super(_texto, _lista, _contenedor);
        setPreferredSize(new Dimension(200, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int x1 = (int) (anchoPanel*0.171);
        int x2 = (int) (anchoPanel*0.828);
        int centro_x = anchoPanel/2;
        int centro_y = altoPanel/2;
        int cuarto = anchoPanel/4;

        //Dibujamos el flujo
        g.setColor(Color.BLACK);
        g.drawLine(x1, 0, x1, centro_y);  //Linea vertical izquierda
        g.drawLine(x1,0,x1+10,+10);
        g.drawLine(x1,0,x1-10,+10);
        g.drawLine(x2, 0, x2, centro_y);  //Linea vertical derecha
        g.drawLine((int)(centro_x*1.5)-40,centro_y,(int)(centro_x*1.5)-30,centro_y+10);
        g.drawLine((int)(centro_x*1.5)-40,centro_y,(int)(centro_x*1.5)-30,centro_y-10);
        g.drawLine(x1,centro_y,x2,centro_y);  //Linea horizontal
        g.drawLine(centro_x,centro_y,centro_x,altoPanel);
    }
}
