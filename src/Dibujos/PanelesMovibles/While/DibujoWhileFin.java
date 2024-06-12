package Dibujos.PanelesMovibles.While;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class DibujoWhileFin extends PanelPersonalizado {

    public DibujoWhileFin(String texto, List<PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(texto,lista,_contenedor,_restriciones,_ventanaEmergente);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int centro_x = panelWidth/2;        // Centro horizontal
        int centro_y = panelHeight/2;     // Centro vertical

        int cuarto = panelWidth/4;

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,panelHeight/2);    // Linea vertical central inferior
        g.drawLine((int) (panelWidth*0.1665),0,(int) (panelWidth*0.1665), centro_y);     // Linea vertical izquierda --------------
        g.drawLine((int) (cuarto*3.33), 0,(int) (cuarto*3.33), (int) (centro_y+centro_y*0.5));   // Linea vertical derecha
        g.drawLine((int) (panelWidth*0.1665), centro_y,centro_x, centro_y); // Linea horizontal izquierda
        g.drawLine(centro_x, (int) (panelHeight*0.75),(int) (cuarto*3.33), (int) (panelHeight*0.75)); // Linea horizontal derecha
        g.drawLine(centro_x, (int) (panelHeight*0.75),centro_x,panelHeight); // Linea inferior

    }
}
