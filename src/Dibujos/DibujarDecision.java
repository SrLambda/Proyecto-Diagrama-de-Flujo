package Dibujos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class DibujarDecision extends PanelPersonalizado{
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;
    public DibujarDecision(String texto, List<PanelPersonalizado> lista, JPanel _contenedor) {
        super(texto,lista,_contenedor);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ultimoEjeY = e.getYOnScreen();
                moviendo = true;
                //System.out.println("Moviendo "+"Desicion"+" "); //Para verificar el movimiento sostenido
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                moviendo = false;
                int indice = colisiones();
                if(indice != -1){
                    intercambiarPosiciones();
                }
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            //Aqui se implementa la toma de un panel y arrastre
            public void mouseDragged(MouseEvent e) {
                if(moviendo){
                    int cambioPosicionY = e.getYOnScreen() - ultimoEjeY;
                    setLocation(getX(), getY() + cambioPosicionY);
                    ultimoEjeY = e.getYOnScreen();
                    colisionesVisual();
                }
            }

            @Override
            //Detectamos la posicion del mouse dentro de un panel
            public void mouseMoved(MouseEvent e) {
                ejeYMouse = e.getY();
                setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                repaint(); //Volvemos a dibujar el panel
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

        g.setColor(Color.YELLOW);
        g.drawLine(x1, centro_y,centro_x, y1);     // Lado superior
        g.drawLine(x2, centro_y,centro_x, y1);     // Lado derecho
        g.drawLine(x1, centro_y,centro_x, y2);     // Lado inferior
        g.drawLine(x2, centro_y,centro_x, y2);     // Lado izquierdo

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,y1);              // Linea superior

        g.drawLine(centro_x,y1,centro_x+10,y1-10);   // Flecha
        g.drawLine(centro_x,y1,centro_x-10,y1-10);   // de flujo

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

    public void intercambiarPosiciones(){
        int indice = colisiones();
        if (indice != -1) {
            PanelPersonalizado panelActual = listaFiguras.get(indice);
            int indiceAnterior = indice - 1;

            if (indiceAnterior >= 0) {
                PanelPersonalizado panelAnterior = listaFiguras.get(indiceAnterior);
                listaFiguras.set(indice, panelAnterior);
                listaFiguras.set(indiceAnterior, panelActual);
                actualizarPosicionesVisuales();
            }
        }
    }

    private void actualizarPosicionesVisuales() {
        for (int i = 0; i < listaFiguras.size(); i++) {
            PanelPersonalizado panel = listaFiguras.get(i);
            panel.setLocation(0, i * panel.getHeight());
        }
        contenedor.repaint();
    }

}
