package Dibujos.PanelesMovibles.Decision;

import Dibujos.PanelPersonalizado;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class DibujoDecisionInicio extends PanelPersonalizado {

    public DibujoDecisionInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones) {
        super(texto, lista, _contenedor,_restriciones);

        addMouseListener(new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) { // Doble clic para editar el texto
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto); // Actualizar el texto de la figura
                    }
                }

            }
        });
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Coordenadas del rombo
        int x1 = (int) ((panelWidth / 4)+panelWidth*0.1);                    // Coordenada x del lado izquierdo del rectángulo
        int x2 = (int) ((panelWidth - (panelWidth / 4))-panelWidth*0.1);     // Coordenada x del lado derecho del rectángulo
        int y1 = (int) ((panelHeight / 4)+panelHeight*0.15);                 // Coordenada y del lado superior del rectángulo
        int y2 = (int) ((panelHeight - (panelHeight / 4))-panelHeight*0.15); // Coordenada y del lado inferior del rectángulo

        int centro_x = panelWidth/2;                                         // Centro horizontal
        int centro_y = panelHeight/2;                                        // Centro vertical

        int cuarto = panelWidth/4;


        // Dibujar las líneas que forman el rombo
        g.setColor(Color.BLACK);
        g.drawLine(x1, centro_y,centro_x, y1);     // Lado superior
        g.drawLine(x2, centro_y,centro_x, y1);     // Lado derecho
        g.drawLine(x1, centro_y,centro_x, y2);     // Lado inferior
        g.drawLine(x2, centro_y,centro_x, y2);     // Lado izquierdo

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,y1);  // Linea superior

        g.drawLine(centro_x,y1,centro_x,y1);   // Flecha
        g.drawLine(centro_x,y1,centro_x,y1);   // de flujo

        g.drawLine(x1,centro_y,cuarto,centro_y);
        g.drawLine(x2,centro_y,cuarto*3,centro_y);
        g.drawLine(cuarto,centro_y,cuarto,panelHeight);
        g.drawLine(cuarto*3,centro_y,cuarto*3,panelHeight);

        // fuente con el tamaño especificado
        g.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }

    //alternativa 2 del paintComponent
    /*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Coordenadas para el ciclo While
        int x = (int) (panelWidth * 0.1);
        int y = (int) (panelHeight * 0.1);
        int width = (int) (panelWidth * 0.8);
        int height = (int) (panelHeight * 0.8);

        // Dibujar el rectángulo para el ciclo While
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        // Dibujar la línea de separación
        int yLine = y + height / 2;
        g.drawLine(x, yLine, x + width, yLine);

        // Dibujar las flechas del ciclo
        int arrowSize = 10; // Tamaño de la punta de la flecha
        int xArrow = x + width / 2;
        int yArrowUp = y - arrowSize;
        int yArrowDown = y + height + arrowSize;

        // Flecha hacia arriba
        g.drawLine(xArrow, y, xArrow, yArrowUp);
        g.drawLine(xArrow, yArrowUp, xArrow - arrowSize, yArrowUp + arrowSize);
        g.drawLine(xArrow, yArrowUp, xArrow + arrowSize, yArrowUp + arrowSize);

        // Flecha hacia abajo
        g.drawLine(xArrow, yLine, xArrow, yArrowDown);
        g.drawLine(xArrow, yArrowDown, xArrow - arrowSize, yArrowDown - arrowSize);
        g.drawLine(xArrow, yArrowDown, xArrow + arrowSize, yArrowDown - arrowSize);

        // Dibujar el texto "While Condición" y "Acción(es)"
        g.setFont(textoFont);
        FontMetrics metrics = g.getFontMetrics();
        String textoCondicion = "While Condición";
        String textoAccion = "Acción(es)";
        int xTextoCondicion = (getWidth() - metrics.stringWidth(textoCondicion)) / 2;
        int yTextoCondicion = y + metrics.getAscent();
        int xTextoAccion = (getWidth() - metrics.stringWidth(textoAccion)) / 2;
        int yTextoAccion = yLine + metrics.getAscent();

        g.drawString(textoCondicion, xTextoCondicion, yTextoCondicion);
        g.drawString(textoAccion, xTextoAccion, yTextoAccion);
    }*/
}
