import Dibujos.*;
import Dibujos.PanelesMovibles.DibujoFor;
import Dibujos.PanelesNoMovibles.DibujoFin;
import Dibujos.PanelesNoMovibles.DibujoInicio;

import javax.swing.*;

import java.awt.*;
import java.util.List;
public class Controlador {
    private static Controlador instancia;
<<<<<<< HEAD
    private List<PanelPersonalizado> listaFiguras;
    private JPanel contenedor;
    private Parseador parseador;
    private VentanaEmergente ventanaEmergente;
    private GridBagConstraints restriciones;
=======
    private final Graficador graficador;
    private static List<PanelPersonalizado> listaFiguras;
    private static JPanel contenedor;
>>>>>>> Edgar


    // Instanciar Singleton
    private Controlador() {
<<<<<<< HEAD

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

=======
        this.graficador = new Graficador();
    }

    public static Controlador getInstancia() {
        if (instancia == null) {
>>>>>>> Edgar
            instancia = new Controlador();
        }
        return instancia;
    }

    //Metodos
<<<<<<< HEAD

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
=======
    public void initFront(Front front, List<PanelPersonalizado> _listaFiguras, JPanel _contenedor) {
        contenedor = _contenedor;
        listaFiguras = _listaFiguras;
        front.getPanel1().setLayout(new BoxLayout(front.getPanel1(), BoxLayout.Y_AXIS));
        front.getPanel1().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    public void crearProceso(Front front) {
        PanelPersonalizado nuevo = new DibujoProceso(entradaDeTexto(), listaFiguras, contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();
        timer(nuevo);
    }


    public void crearImpresion(Front front) {
        PanelPersonalizado nuevo = new DibujoDocumento(entradaDeTexto(), listaFiguras, contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
>>>>>>> Edgar
        front.getPanel1().revalidate();
        timer(nuevo);
    }

<<<<<<< HEAD
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
        PanelPersonalizado nuevo = factory.crearPanel(tipo,texto,listaFiguras,contenedor,restriciones);

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

=======

    public void crearInicio(Front front) {
        PanelPersonalizado nuevo = new DibujoInicio("Inicio", listaFiguras, contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();
    }

    public void crearFin(Front front) {
        PanelPersonalizado nuevo = new DibujoFin("Fin", listaFiguras, contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();
    }

    public void crearDecision(Front front) {
        PanelPersonalizado nuevo = new DibujarDecision(entradaDeTexto(), listaFiguras, contenedor);
        PanelPersonalizado aux = new DibujoDecisionFin("", listaFiguras, contenedor);
        listaFiguras.add(nuevo);
        listaFiguras.add(aux);
        front.getPanel1().add(nuevo);
        front.getPanel1().add(aux);
        front.getPanel1().revalidate();
    }

    public void crearSalida(Front front) {
        PanelPersonalizado nuevo = new DibujoSalida(entradaDeTexto(), listaFiguras, contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();
    }

    public void crearEntreda(Front front) {
        PanelPersonalizado nuevo = new DibujoEntrada(entradaDeTexto(), listaFiguras, contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();
    }


    public void limpiarPantalla(Front front) {
        this.graficador.limpiarPantalla(front);
    }

    //===============================================================================================================

    private static class Graficador {
        public void dibujarProceso(JPanel lienzo) {
            Graphics g = lienzo.getGraphics();
            g.setColor(Color.BLUE);
            g.drawLine(0, 50, 100, 50);   // Línea superior
            g.drawLine(0, 50, 0, 10);     // Línea izquierda
            g.drawLine(100, 50, 100, 10); // Línea derecha
            g.drawLine(0, 10, 100, 10);   // Línea inferior
        }

        public void dibujarImpresion(Front front) {
            JPanel lienzo = front.getPanel1();

            QuadCurve2D curve = new QuadCurve2D.Double();
            Graphics2D lapiz = (Graphics2D) lienzo.getGraphics();
            lapiz.setColor(Color.CYAN);

            lapiz.drawLine(100, 100, 200, 100);
            lapiz.drawLine(200, 100, 200, 140);
            lapiz.drawLine(100, 100, 100, 150);
            curve.setCurve(100, 150, 125, 165, 150, 150);
            lapiz.draw(curve);
            curve.setCurve(150, 150, 175, 135, 200, 140);
            lapiz.draw(curve);

        }


        public void dibujarInicio(Front front) {
            QuadCurve2D curve = new QuadCurve2D.Double();
            Graphics2D lapiz = (Graphics2D) front.getGraphics();

            lapiz.setColor(Color.MAGENTA);
            lapiz.drawLine(50, 200, 150, 200);
            lapiz.drawLine(50, 250, 150, 250);
            curve.setCurve(50, 200, 30, 205, 30, 225);
            lapiz.draw(curve);
            curve.setCurve(50, 250, 30, 245, 30, 225);
            lapiz.draw(curve);
            curve.setCurve(150, 200, 170, 205, 170, 225);
            lapiz.draw(curve);
            curve.setCurve(150, 250, 170, 245, 170, 225);
            lapiz.draw(curve);
        }

        public void dibujarFin(Front front) {
            QuadCurve2D curve = new QuadCurve2D.Double();
            Graphics2D lapiz = (Graphics2D) front.getGraphics();

            lapiz.setColor(Color.BLACK);
            lapiz.drawLine(50, 200, 150, 200);
            lapiz.drawLine(50, 250, 150, 250);
            curve.setCurve(50, 200, 30, 205, 30, 225);
            lapiz.draw(curve);
            curve.setCurve(50, 250, 30, 245, 30, 225);
            lapiz.draw(curve);
            curve.setCurve(150, 200, 170, 205, 170, 225);
            lapiz.draw(curve);
            curve.setCurve(150, 250, 170, 245, 170, 225);
            lapiz.draw(curve);
        }

        public void dibujarDecision(Front front) {
            Graphics g = front.getGraphics();
            g.setColor(Color.RED);
            g.drawLine(100, 70, 150, 100);
            g.drawLine(150, 100, 100, 130);
            g.drawLine(100, 130, 50, 100);
            g.drawLine(50, 100, 100, 70);
        }


        public void dibujarEntradaSalida(Front front) {
            Graphics g = front.getGraphics();
            g.setColor(Color.orange);
            g.drawLine(125, 150, 250, 150);
            g.drawLine(250, 150, 225, 200);
            g.drawLine(225, 200, 100, 200);
            g.drawLine(100, 200, 125, 150);
        }


        public void limpiarPantalla(Front front) {
            JPanel lienzo = front.getPanel1();

            lienzo.removeAll();
            lienzo.repaint();
        }
    }
    //===============================================================================================================
>>>>>>> Edgar

    public static String entradaDeTexto() {
        JTextField textField = new JTextField();
        Object[] message = {
                "Ingrese datos:", textField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Datos", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            return textField.getText();
        } else {
            return "----";
        }
    }
<<<<<<< HEAD
=======

    public void timer(PanelPersonalizado nuevo) {
        Timer timer = new Timer(100, e -> {
            nuevo.actualizarUbicacion();

            for (PanelPersonalizado figura : listaFiguras) {
                figura.actualizarUbicacion();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
>>>>>>> Edgar
}
