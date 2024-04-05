package Dibujos;

import java.awt.*;

public class DibujoDecisionFin extends PanelPersonalizado{
    public DibujoDecisionFin(String texto) {
        super(texto);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



        int panelWidth = getWidth();
        int panelHeight = getHeight();


        int centro_x = panelWidth/2;        // Centro horizontal
        int centro_y = panelHeight/2;     // Centro vertical

        int cuarto = panelWidth/4;


        // Dibujar las líneas que forman el rectángulo

        g.setColor(Color.BLUE);
        g.drawLine(cuarto,0,cuarto, centro_y);                // Linea derecha
        g.drawLine(cuarto*3, 0,cuarto*3, centro_y);   // Linea izquierda
        g.drawLine(cuarto, centro_y,cuarto*3, centro_y);     // Linea central
        g.drawLine(centro_x, centro_y,centro_x, panelHeight);    // Linea baja


    }
}
