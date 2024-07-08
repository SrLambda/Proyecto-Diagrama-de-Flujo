package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class DibujoForFin extends PanelPersonalizado {

    public DibujoForFin(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                        VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto,lista,_contenedor,_restriciones,_ventanaEmergente,_variables);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int centro_x = anchoPanel/2;
        int centro_y = altoPanel/2;
        int cuarto = anchoPanel/4;

        //Dibujamos el flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(cuarto, 0, cuarto, centro_y);  //Linea vertical izquierda
        g2d.drawLine(cuarto,0,cuarto+10,+10);
        g2d.drawLine(cuarto,0,cuarto-10,+10);
        g2d.drawLine(cuarto*3, 0, cuarto*3, centro_y);  //Linea vertical derecha
        g2d.drawLine(centro_x+50,centro_y,centro_x+60,centro_y+10);
        g2d.drawLine(centro_x+50,centro_y,centro_x+60,centro_y-10);
        g2d.drawLine(cuarto,centro_y,cuarto*3,centro_y);  //Linea horizontal
        g2d.drawLine(centro_x,centro_y,centro_x,altoPanel);
    }
}
