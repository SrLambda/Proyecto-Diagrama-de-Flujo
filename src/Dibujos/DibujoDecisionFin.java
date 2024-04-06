package Dibujos;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DibujoDecisionFin extends PanelPersonalizado{
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;
    public DibujoDecisionFin(String texto) {
        super(texto);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ultimoEjeY = e.getYOnScreen();
                moviendo = true;
                //System.out.println("Moviendo "+"DesicionFin"+" "); //Para verificar el movimiento sostenido
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                moviendo = false;
                //System.out.println("Soltado "+"DesicionFin"+" "); //Para verificar el termino del movimiento sostenido
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


        int centro_x = panelWidth/2;        // Centro horizontal
        int centro_y = panelHeight/2;     // Centro vertical

        int cuarto = panelWidth/4;


        // Dibujar las líneas que forman el rectángulo

        g.setColor(Color.BLACK);
        g.drawLine(cuarto,0,cuarto, centro_y);                // Linea derecha
        g.drawLine(cuarto*3, 0,cuarto*3, centro_y);   // Linea izquierda
        g.drawLine(cuarto, centro_y,cuarto*3, centro_y);     // Linea central
        g.drawLine(centro_x, centro_y,centro_x, panelHeight);    // Linea baja


    }
}
