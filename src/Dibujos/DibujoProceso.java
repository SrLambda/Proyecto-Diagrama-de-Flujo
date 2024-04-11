package Dibujos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class DibujoProceso extends PanelPersonalizado {
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;
    public DibujoProceso(String texto, List <PanelPersonalizado> lista, JPanel _contenedor) {
        super(texto,lista,_contenedor);
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

        // Para editar texto ya ingresado
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) { // Doble clic para editar el texto
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto); // Actualizar el texto de la figura
                    }
                }

                // Verificar si se hizo clic derecho
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int option = JOptionPane.showConfirmDialog(null, "¿Eliminar esta figura?", "Eliminar Figura", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        //eliminarFigura();
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

        // Coordenadas del rectángulo
        int x1 = (int) ((panelWidth / 4)+panelWidth*0.1);                  // Coordenada x del lado izquierdo del rectángulo
        int x2 = (int) ((panelWidth - (panelWidth / 4))-panelWidth*0.1);   // Coordenada x del lado derecho del rectángulo
        int y1 = (int) ((panelHeight / 4)+panelHeight*0.15);                 // Coordenada y del lado superior del rectángulo
        int y2 = (int) ((panelHeight - (panelHeight / 4))-panelHeight*0.15); // Coordenada y del lado inferior del rectángulo

        // Coordenadas de Flujo
        int centro_x = panelWidth/2;


        // Dibujar las líneas que forman el rectángulo

        g.setColor(Color.MAGENTA);
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

    // Para eliminar una figura seleccionada
    /*public void eliminarFigura() {
        super.eliminarFigura();
    }*/
}
