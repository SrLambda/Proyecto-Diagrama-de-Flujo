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
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int centro_x = panelWidth/2;
        int centro_y = panelHeight/2;
        int cuarto = panelWidth/4;

        //Dibujamos el flujo
        g.drawLine(centro_x,0,centro_x,panelHeight/2);    // Linea vertical central inferior
        g.drawLine((int) (panelWidth*0.1665),0,(int) (panelWidth*0.1665), centro_y);     // Linea vertical izquierda --------------
        g.drawLine((int) (cuarto*3.33), 0,(int) (cuarto*3.33), (int) (centro_y+centro_y*0.5));   // Linea vertical derecha
        g.drawLine((int) (panelWidth*0.1665), centro_y,centro_x, centro_y); // Linea horizontal izquierda
        g.drawLine(centro_x, (int) (panelHeight*0.75),(int) (cuarto*3.33), (int) (panelHeight*0.75)); // Linea horizontal derecha
        g.drawLine(centro_x, (int) (panelHeight*0.75),centro_x,panelHeight); // Linea inferior

    }
}
