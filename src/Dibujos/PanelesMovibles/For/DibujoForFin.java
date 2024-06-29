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
        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int x1 = (int) (anchoPanel*0.171);
        int x2 = (int) (anchoPanel*0.828);
        int centro_x = anchoPanel/2;
        int centro_y = altoPanel/2;
        int cuarto = anchoPanel/4;

        //Dibujamos el flujo
        g.setColor(Color.BLACK);
        g.drawLine(cuarto, 0, cuarto, centro_y);  //Linea vertical izquierda
        g.drawLine(cuarto,0,cuarto+10,+10);
        g.drawLine(cuarto,0,cuarto-10,+10);
        g.drawLine(cuarto*3, 0, cuarto*3, centro_y);  //Linea vertical derecha
        g.drawLine(centro_x+50,centro_y,centro_x+60,centro_y+10);
        g.drawLine(centro_x+50,centro_y,centro_x+60,centro_y-10);
        g.drawLine(cuarto,centro_y,cuarto*3,centro_y);  //Linea horizontal
        g.drawLine(centro_x,centro_y,centro_x,altoPanel);
    }
}
