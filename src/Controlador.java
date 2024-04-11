import Dibujos.*;
import Dibujos.PanelesMovibles.*;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionFin;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionInicio;
import Dibujos.PanelesNoMovibles.DibujoFin;
import Dibujos.PanelesNoMovibles.DibujoInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.List;

public class Controlador {


    private static Controlador instancia;
    private List <PanelPersonalizado> listaFiguras;
    private JPanel contenedor;


    // Instanciar Singleton
    private Controlador()
    {

    }

    public static Controlador getInstancia()
    {

        if (instancia == null)
        {

            instancia = new Controlador();

        }

        return instancia;

    }


    //Metodos

    public void initFront(Front front, List <PanelPersonalizado> _listaFiguras,JScrollPane scroll, JPanel _contenedor)
    {
        contenedor = _contenedor;
        listaFiguras = _listaFiguras;
        front.getPanel1().setLayout(new BoxLayout(front.getPanel1(), BoxLayout.Y_AXIS));
        front.getPanel1().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Barra de desplazamiento vertical siempre visible
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Sin barra de desplazamiento horizontal
        scroll.setBorder(BorderFactory.createEmptyBorder());

        crearInicio(front);
        crearFin(front);

    }


    public void crearProceso(Front front)
    {
        PanelPersonalizado nuevo = new DibujoProceso(entradaDeTexto(),listaFiguras,contenedor);

        int posicion = listaFiguras.size()-1;
        listaFiguras.add(posicion, nuevo);
        front.getPanel1().add(nuevo,posicion);
        front.getPanel1().revalidate();
        timer(nuevo);

    }

    public void crearInicio(Front front)
    {
        PanelPersonalizado nuevo = new DibujoInicio("Inicio",listaFiguras,contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();

    }

    public void crearFin(Front front)
    {
        PanelPersonalizado nuevo = new DibujoFin("Fin",listaFiguras,contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();

    }

    //===========================================================================================


    public void crearImpresion(Front front)
    {

        PanelPersonalizado nuevo = new DibujoDocumento(entradaDeTexto(),listaFiguras,contenedor);

        int posicion = listaFiguras.size()-1;

        listaFiguras.add(posicion, nuevo);
        front.getPanel1().add(nuevo,posicion);
        front.getPanel1().revalidate();
        timer(nuevo);
    }


    public void crearDecision(Front front)
    {
        PanelPersonalizado nuevo = new DibujoDecision(entradaDeTexto(),listaFiguras,contenedor);

        int posicion = listaFiguras.size()-1;

        listaFiguras.add(posicion,nuevo);
        front.getPanel1().add(nuevo,posicion);

        front.getPanel1().revalidate();
    }

    public void crearSalida(Front front)
    {
        PanelPersonalizado nuevo = new DibujoSalida(entradaDeTexto(),listaFiguras,contenedor);

        int posicion = listaFiguras.size()-1;

        listaFiguras.add(posicion, nuevo);
        front.getPanel1().add(nuevo,posicion);
        front.getPanel1().revalidate();

    }

    public void crearEntreda(Front front)
    {
        PanelPersonalizado nuevo = new DibujoEntrada(entradaDeTexto(),listaFiguras,contenedor);

        int posicion = listaFiguras.size()-1;

        listaFiguras.add(posicion, nuevo);
        front.getPanel1().add(nuevo,posicion);
        front.getPanel1().revalidate();

    }


    public void limpiarPantalla(Front front)
    {

        JPanel lienzo = front.getPanel1();

        lienzo.removeAll();
        lienzo.repaint();

    }


    //===============================================================================================================


    public static String entradaDeTexto() {

        JTextField textField = new JTextField();

        Object[] message = {
                "Ingrese datos:", textField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Datos", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION)
        {
            return textField.getText();

        }
        else
        {
            return "----";

        }
    }

    public void timer(PanelPersonalizado nuevo){
        Timer timer = new Timer(100, e -> {
            nuevo.actualizarUbicacion();

            for (PanelPersonalizado figura : listaFiguras) {
                figura.actualizarUbicacion();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
