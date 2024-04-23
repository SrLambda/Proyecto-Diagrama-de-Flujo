package Dibujos.PanelesMovibles.Decision;


import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DibujoDecision;
import Mapa.Mapa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class DibujoDecisionInicio extends PanelPersonalizado {

    private DibujoDecisionInterno interno;

    public DibujoDecisionInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoDecisionInterno _interno, Mapa _mapa,Integer _alto) {
        super(texto,lista,_contenedor);
        this.interno = _interno;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) { // Doble clic para editar el texto
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto); // Actualizar el texto de la figura
                    }
                }

                if (SwingUtilities.isRightMouseButton(e)) {

                    //Verdad
                    JPanel ver= interno.getVerdadero();
                    List<PanelPersonalizado> l_ver= interno.getListaVerdadera();


                    //Falso
                    JPanel fal= interno.getFalso();
                    List<PanelPersonalizado> l_fal= interno.getListaFalsa();


                    new VentanaEmergenteDecision(ver,fal,l_ver,l_fal,(PanelPersonalizado) _contenedor,_mapa,_alto);
                }
            }
        });

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


        // Dibujar las líneas que forman el rombo
        g.setColor(Color.BLACK);
        g.drawLine(x1, centro_y,centro_x, y1);     // Lado superior
        g.drawLine(x2, centro_y,centro_x, y1);     // Lado derecho
        g.drawLine(x1, centro_y,centro_x, y2);     // Lado inferior
        g.drawLine(x2, centro_y,centro_x, y2);     // Lado izquierdo

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,y1);              // Linea superior

        g.drawLine(centro_x,y1,centro_x,y1);   // Flecha
        g.drawLine(centro_x,y1,centro_x,y1);   // de flujo

        g.drawLine(x1,centro_y,cuarto,centro_y);
        g.drawLine(x2,centro_y,cuarto*3,centro_y);
        g.drawLine(cuarto,centro_y,cuarto,panelHeight);
        g.drawLine(cuarto*3,centro_y,cuarto*3,panelHeight);


        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }
}
