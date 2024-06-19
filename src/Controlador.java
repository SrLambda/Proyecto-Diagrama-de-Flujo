import Dibujos.*;
import Dibujos.PanelesMovibles.DibujoFor;
import Dibujos.PanelesNoMovibles.DibujoFin;
import Dibujos.PanelesNoMovibles.DibujoInicio;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controlador {
    private static Controlador instancia;
    private List<PanelPersonalizado> listaFiguras;
    private JPanel contenedor;
    private Parseador parseador;
    private VentanaEmergente ventanaEmergente;
    private GridBagConstraints restriciones;
    private List <Object> variables;


    // Instanciar Singleton
    private Controlador() {

        this.restriciones         = new GridBagConstraints();
        this.restriciones.gridx   = 0;
        this.restriciones.gridy   = GridBagConstraints.RELATIVE; // Se inicia en la siguiente fila
        this.restriciones.anchor  = GridBagConstraints.CENTER; // Alineación vertical superior
        this.restriciones.fill    = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
        this.restriciones.weighty = 0; // No expandir en dirección vertical
        this.restriciones.insets  = new Insets(0, 0, 0, 0); // Sin espacio entre paneles
        this.variables = new ArrayList<>();
        inicializarLista();
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



        front.getPseudocodio().setLayout(new BoxLayout(front.getPseudocodio(), BoxLayout.Y_AXIS));
        front.getPseudocodio().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Barra de desplazamiento vertical siempre visible
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // Sin barra de desplazamiento horizontal
        scroll.setBorder(BorderFactory.createEmptyBorder());

        crearInicio(front);
        crearFin(front);

        this.ventanaEmergente = new VentanaEmergente(_listaFiguras,_contenedor,this.restriciones);

    }

    private void crearInicio(Front front)
    {
        PanelPersonalizado nuevo = new DibujoInicio("Inicio",listaFiguras,contenedor,restriciones,ventanaEmergente,variables);

        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo,this.restriciones);
        front.getPanel1().revalidate();


    }

    private void crearFin(Front front)
    {
        PanelPersonalizado nuevo = new DibujoFin("Fin",listaFiguras,contenedor,restriciones,ventanaEmergente,variables);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo,this.restriciones);
        front.getPanel1().revalidate();

    }

    //===========================================================================================


    public void crearPanel(Front front, String tipo)
    {
        // Clase Factory
        FactoryPanel factory = new FactoryPanel();

        String texto = "---";

        if(!tipo.equals("for"))
        {
            texto = entradaDeTexto();
        }

        // Crea el panel
        PanelPersonalizado nuevo = factory.crearPanel(tipo,texto,listaFiguras,contenedor,restriciones,ventanaEmergente,variables);

        if(nuevo instanceof DibujoFor)
        {
            DibujoFor aux = (DibujoFor) nuevo;
            aux.modificarValores();
        }

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
        JPanel pseudo = front.getPseudocodio();

        pseudo.removeAll();
        lienzo.removeAll();
        lienzo.revalidate();
        pseudo.revalidate();

    }

    public void pseudoCodigo(Front front)
    {
        parseador.actualizar();

        JPanel contenedor = front.getPseudocodio();

        contenedor.removeAll();


        JTextArea pseudocodigo = new JTextArea(this.parseador.getPseuddoCodigo());
        pseudocodigo.setLineWrap(true);
        pseudocodigo.setWrapStyleWord(true);
        pseudocodigo.setEditable(false);

        contenedor.add(pseudocodigo);

        contenedor.revalidate();
        contenedor.repaint();
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

    public void inicializarLista(){
        int j = 100;
        for(int i=0; i<100; i++){
            variables.add("Catacresis");
        }
    }

}
