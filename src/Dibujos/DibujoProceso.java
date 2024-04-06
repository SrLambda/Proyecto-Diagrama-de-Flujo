package Dibujos;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DibujoProceso extends PanelPersonalizado {
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;
    public DibujoProceso(String texto) {
        super(texto);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ultimoEjeY = e.getYOnScreen();
                moviendo = true;
                //System.out.println("Moviendo "+"Proceso"+" "); //Para verificar el movimiento sostenido
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                moviendo = false;
                //System.out.println("Soltado "+"Proceso"+" "); //Para verificar el termino del movimiento sostenido
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
        int x1 = (int) ((panelWidth / 4)+panelWidth*0.1);                  // Coordenada x del lado izquierdo del rectángulo
        int x2 = (int) ((panelWidth - (panelWidth / 4))-panelWidth*0.1);   // Coordenada x del lado derecho del rectángulo
        int y1 = (int) ((panelHeight / 4)+panelHeight*0.15);                 // Coordenada y del lado superior del rectángulo
        int y2 = (int) ((panelHeight - (panelHeight / 4))-panelHeight*0.15); // Coordenada y del lado inferior del rectángulo

        // Coordenadas de Flujo
        int centro_x = panelWidth/2;


        // Dibujar las líneas que forman el rectángulo

        g.setColor(Color.CYAN);
        g.drawLine(x1, y1, x2, y1);     // Lado superior
        g.drawLine(x2, y1, x2, y2);     // Lado derecho
        g.drawLine(x2, y2, x1, y2);     // Lado inferior
        g.drawLine(x1, y2, x1, y1);     // Lado izquierdo

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,y1);        // Linea superior
        g.drawLine(centro_x,y2,centro_x,panelHeight);  // Linea inferior
        g.drawLine(centro_x,y1,centro_x+10,y1-10);
        g.drawLine(centro_x,y1,centro_x-10,y1-10);


        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }
}
