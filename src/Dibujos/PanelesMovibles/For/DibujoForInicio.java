package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class DibujoForInicio extends PanelPersonalizado {

    private DibujoForInterno interno;
    private List<PanelPersonalizado> panelesCiclo;

    public DibujoForInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoForInterno _interno,
                           GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        this.interno = _interno;
        panelesCiclo = lista;


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Coordenadas del rectángulo
        int x1 = (int) ((panelWidth / 4)+panelWidth*0.1);                    // Coordenada x del lado izquierdo del rectángulo
        int x2 = (int) ((panelWidth - (panelWidth / 4))-panelWidth*0.1);     // Coordenada x del lado derecho del rectángulo
        int y1 = (int) ((panelHeight / 4)+panelHeight*0.15);                 // Coordenada y del lado superior del rectángulo
        int y2 = (int) ((panelHeight - (panelHeight / 4))-panelHeight*0.15); // Coordenada y del lado inferior del rectángulo

        int centro_x = panelWidth/2;                                         // Centro horizontal
        int centro_y = panelHeight/2;                                        // Centro vertical

        int cuarto = panelWidth/4;

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,centro_y);
        g.drawLine(centro_x-50,centro_y,centro_x-60,centro_y+10);   //Flecha
        g.drawLine(centro_x-50,centro_y,centro_x-60,centro_y-10);
        g.setColor(Color.BLACK);
        g.drawLine(cuarto,centro_y,cuarto*3,centro_y); //Linea completa
        g.drawLine(cuarto,centro_y,cuarto,panelHeight);
        g.drawLine(cuarto*3,centro_y,cuarto*3,panelHeight);
        g.drawLine(cuarto*3,panelHeight,cuarto*3-10,panelHeight-10); //Flecha
        g.drawLine(cuarto*3,panelHeight,cuarto*3+10,panelHeight-10);

    }

    public List<PanelPersonalizado> getPanelesCiclo() {
        return panelesCiclo;
    }

}
