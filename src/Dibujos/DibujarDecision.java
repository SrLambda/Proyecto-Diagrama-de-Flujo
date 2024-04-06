package Dibujos;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DibujarDecision extends PanelPersonalizado{
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;
    public DibujarDecision(String texto) {
        super(texto);
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
                //System.out.println("Soltado "+"Desicion"+" "); //Para verificar el termino del movimiento sostenido
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
}
