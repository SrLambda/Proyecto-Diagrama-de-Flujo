import Dibujos.*;
import Dibujos.PanelesNoMovibles.DibujoFin;
import Dibujos.PanelesNoMovibles.DibujoInicio;
import Mapa.Mapa;

import javax.swing.*;
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

    private void crearInicio(Front front)
    {
        PanelPersonalizado nuevo = new DibujoInicio("Inicio",listaFiguras,contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();

    }

    private void crearFin(Front front)
    {
        PanelPersonalizado nuevo = new DibujoFin("Fin",listaFiguras,contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();

    }

    //===========================================================================================


    public void crearPanel(Front front, String tipo, Mapa _mapa)
    {
        FactoryPanel factory = new FactoryPanel();

        PanelPersonalizado nuevo = factory.crearPanel(tipo,entradaDeTexto(),listaFiguras,contenedor,_mapa);

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
}
