package Dibujos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class DibujoDecisionFin extends PanelPersonalizado{
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;
    public DibujoDecisionFin(String texto, List<PanelPersonalizado> lista, JPanel _contenedor) {
        super(texto,lista,_contenedor);
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

    public void intercambiarPosiciones() {
        int indice = colisiones();
        if (indice != -1 && listaFiguras.get(indice).habilitado) {
            PanelPersonalizado tempPosicion = listaFiguras.get(posicion);
            PanelPersonalizado tempColision = listaFiguras.get(indice);
            listaFiguras.set(this.posicion, tempColision);
            listaFiguras.set(indice, tempPosicion);
            actualizarPosicionesVisuales();
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
