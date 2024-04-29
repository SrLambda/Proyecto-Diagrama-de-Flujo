package Dibujos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

public abstract class PanelMovible extends PanelPersonalizado{

    protected int ultimoEjeY;
    protected boolean moviendo;
    protected int ejeYMouse;

    public PanelMovible(String texto, List<PanelPersonalizado> lista, JPanel _contenedor) {
        super(texto, lista, _contenedor);
        if(this.posOriginal == -1){
            this.posOriginal = getY();
        }

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

                // Verificar si se hizo clic derecho
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int option = JOptionPane.showConfirmDialog(null, "¿Eliminar esta figura?", "Eliminar Figura", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        eliminarFigura();
                    }
                }
            }



            // Click de mouse
            @Override
            public void mousePressed(MouseEvent e)
            {

                ultimoEjeY = e.getYOnScreen();
                moviendo = true;

            }


            // Soltar click de mouse
            @Override
            public void mouseReleased(MouseEvent e)
            {
                moviendo = false;
                int indice = colisiones();

                if(indice != -1)
                {

                    intercambiarPosiciones();

                }

            }




        });

        addMouseMotionListener(new MouseMotionListener() {


            //Aqui se implementa la toma de un panel y arrastre
            @Override
            public void mouseDragged(MouseEvent e)
            {

                if(moviendo)
                {

                    int cambioPosicionY = e.getYOnScreen() - ultimoEjeY;
                    setLocation(getX(), getY() + cambioPosicionY);
                    ultimoEjeY = e.getYOnScreen();
                    colisionesVisual();

                }
            }

            //Detectamos la posicion del mouse dentro de un panel
            @Override

            public void mouseMoved(MouseEvent e)
            {

                ejeYMouse = e.getY();
                setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                repaint(); //Volvemos a dibujar el panel

            }
        });
    }

    public void intercambiarPosiciones()
    {
        int indice = colisiones();

        if (indice != -1 && listaFiguras.get(indice).habilitado)
        {

            PanelPersonalizado tempPosicion = listaFiguras.get(posicion);
            PanelPersonalizado tempColision = listaFiguras.get(indice);
            listaFiguras.set(this.posicion, tempColision);
            listaFiguras.set(indice, tempPosicion);
            actualizarPosicionesVisuales();

        }
    }

    protected void actualizarPosicionesVisuales()
    {

        int y = 0;
        for (PanelPersonalizado panel : listaFiguras)
        {

            panel.setLocation(0, y);
            y += panel.getHeight();

        }
        contenedor.repaint();

    }
}