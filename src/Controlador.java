import Dibujos.*;
import Dibujos.PanelesNoMovibles.DibujoFin;
import Dibujos.PanelesNoMovibles.DibujoInicio;

import javax.swing.*;

import java.awt.*;
import java.util.List;

public class Controlador {


    private static Controlador instancia;
    private List<PanelPersonalizado> listaFiguras;
    private JPanel contenedor;
    private Parseador parseador;
    private VentanaEmergente ventanaEmergente;
    private GridBagConstraints restriciones;


    // Instanciar Singleton
    private Controlador() {

        this.restriciones         = new GridBagConstraints();
        this.restriciones.gridx   = 0;
        this.restriciones.gridy   = GridBagConstraints.RELATIVE; // Se inicia en la siguiente fila
        this.restriciones.anchor  = GridBagConstraints.CENTER; // Alineación vertical superior
        this.restriciones.fill    = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
        this.restriciones.weighty = 0; // No expandir en dirección vertical
        this.restriciones.insets  = new Insets(0, 0, 0, 0); // Sin espacio entre paneles
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
        this.contenedor   = _contenedor;
        this.listaFiguras = _listaFiguras;
        this.parseador    = new Parseador(_listaFiguras);


        /*
        front.getPanel1().setLayout(new BoxLayout(front.getPanel1(), BoxLayout.Y_AXIS));
        front.getPanel1().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        */
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Barra de desplazamiento vertical siempre visible
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // Sin barra de desplazamiento horizontal
        scroll.setBorder(BorderFactory.createEmptyBorder());

        crearInicio(front);
        crearFin(front);

        this.ventanaEmergente = new VentanaEmergente(_listaFiguras,_contenedor,this.restriciones);

    }

    private void crearInicio(Front front)
    {
        PanelPersonalizado nuevo = new DibujoInicio("Inicio",listaFiguras,contenedor,restriciones);

        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo,this.restriciones);
        front.getPanel1().revalidate();


    }

    private void crearFin(Front front)
    {
        PanelPersonalizado nuevo = new DibujoFin("Fin",listaFiguras,contenedor,restriciones);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo,this.restriciones);
        front.getPanel1().revalidate();

    }

    //===========================================================================================


    public void crearPanel(Front front,String tipo)
    {
        // Clase Factory
        FactoryPanel factory = new FactoryPanel();


        // Crea el panel                                   __   cambio   __
        PanelPersonalizado nuevo = factory.crearPanel(tipo,entradaDeTexto(),listaFiguras,contenedor,restriciones);



        //---------------cambios-----------------
        //posicion
        this.ventanaEmergente.agregar(nuevo);
        //---------------------------------------


        // Actualizan los cambios
        this.ventanaEmergente.actualizarCompnentes();
        front.getPanel1().revalidate();

    }


    public void limpiarPantalla(Front front)
    {

        JPanel lienzo = front.getPanel1();

        lienzo.removeAll();
        lienzo.repaint();

    }

    public void pseudoCodigo(Front front)
    {
        parseador.actualizar();
        System.out.println(parseador.getPseuddoCodigo());
    }

    public void prueba(){
        this.ventanaEmergente.mostrar();
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
