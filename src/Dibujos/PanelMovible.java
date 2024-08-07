package Dibujos;
import Dibujos.PanelesMovibles.*;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionInicio;
import Dibujos.PanelesMovibles.DoWhile.DibujoDoWhileFin;
import Dibujos.PanelesMovibles.While.DibujoWhileInicio;
import Dibujos.Ventana.VentanaEmergente;
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
    public PanelMovible(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                        VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        if(this.posOriginal == -1){
            this.posOriginal = getY();
        }

        addMouseListener(new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", PanelMovible.this.texto);
                    if(PanelMovible.this instanceof DibujoEntrada){
                        cambiarTextoAvanzado(nuevoTexto);
                    }
                    if(PanelMovible.this instanceof DibujoProceso){
                        cambiarTextoAvanzado(nuevoTexto);
                    }
                    if(PanelMovible.this instanceof DibujoDocumento){
                        cambiarTextoAvanzado(nuevoTexto);
                    }
                    if(PanelMovible.this instanceof DibujoSalida){
                        cambiarTextoAvanzado(nuevoTexto);
                    }
                    if(PanelMovible.this instanceof DibujoDecisionInicio){
                        cambiarTextoAvanzado(nuevoTexto);
                    }
                    if(PanelMovible.this instanceof DibujoDoWhileFin){
                        cambiarTextoAvanzado(nuevoTexto);
                    }
                    if(PanelMovible.this instanceof DibujoWhileInicio){
                        cambiarTextoAvanzado(nuevoTexto);
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int option = JOptionPane.showConfirmDialog(null, "¿Eliminar esta figura?", "Eliminar Figura", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        for(int i=0; i < variables.size(); i++){
                            if(variables.get(i).equals(PanelMovible.this.getTipo())){
                                System.out.println("Eliminando..."+variables.get(i)+" / "+variables.get(i+1));
                                variables.remove(i);
                                variables.remove(i);
                                break;
                            }
                        }
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

        this.contenedor.removeAll();
        for (PanelPersonalizado panel : listaFiguras)
        {

            this.contenedor.add(panel,restriciones);
        }
        contenedor.repaint();
        contenedor.revalidate();

    }

    public void cambiarTextoAvanzado(String _txt){
        if(this instanceof DibujoEntrada){
            ((DibujoEntrada) this).manejarSalidas(_txt);
        }
        if(this instanceof DibujoSalida){
            ((DibujoSalida) this).manejoSalidas(_txt);
        }
        if(this instanceof DibujoProceso){
            ((DibujoProceso) this).manejo(_txt);
        }
        if(this instanceof DibujoDecisionInicio){
            ((DibujoDecisionInicio) this).manejoSalidas(_txt);
        }
        if(this instanceof DibujoDocumento){
            ((DibujoDocumento) this).manejoSalidas(_txt);
        }
        if(this instanceof DibujoWhileInicio){
            ((DibujoWhileInicio) this).manejoSalidas(_txt);
        }
        if(this instanceof DibujoDoWhileFin){
            ((DibujoDoWhileFin) this).manejoSalida(_txt);
        }
    }

}
