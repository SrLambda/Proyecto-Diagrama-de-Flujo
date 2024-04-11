import Dibujos.*;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionFin;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionInicio;
import Dibujos.PanelesMovibles.DibujoDocumento;
import Dibujos.PanelesMovibles.DibujoEntrada;
import Dibujos.PanelesMovibles.DibujoProceso;
import Dibujos.PanelesMovibles.DibujoSalida;
import Dibujos.PanelesNoMovibles.DibujoFin;
import Dibujos.PanelesNoMovibles.DibujoInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.List;

public class Controlador {


    private static Controlador instancia;
    private final Graficador graficador;
    private List <PanelPersonalizado> listaFiguras;
    private JPanel contenedor;


    // Instanciar Singleton
    private Controlador()
    {

        this.graficador = new Graficador();
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

    }


    public void crearProceso(Front front)
    {
        PanelPersonalizado nuevo = new DibujoProceso(entradaDeTexto(),listaFiguras,contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();
        timer(nuevo);

    }


    public void crearImpresion(Front front)
    {

        PanelPersonalizado nuevo = new DibujoDocumento(entradaDeTexto(),listaFiguras,contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
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

    public void crearDecision(Front front)
    {
        PanelPersonalizado nuevo = new DibujoDecisionInicio(entradaDeTexto(),listaFiguras,contenedor);
        PanelPersonalizado aux = new DibujoDecisionFin("",listaFiguras,contenedor);
        listaFiguras.add(nuevo);
        listaFiguras.add(aux);
        front.getPanel1().add(nuevo);
        front.getPanel1().add(aux);
        front.getPanel1().revalidate();
    }

    public void crearSalida(Front front)
    {
         PanelPersonalizado nuevo = new DibujoSalida(entradaDeTexto(),listaFiguras,contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();

    }

    public void crearEntreda(Front front)
    {
        PanelPersonalizado nuevo = new DibujoEntrada(entradaDeTexto(),listaFiguras,contenedor);
        listaFiguras.add(nuevo);
        front.getPanel1().add(nuevo);
        front.getPanel1().revalidate();

    }


    public void limpiarPantalla(Front front)
    {

        this.graficador.limpiarPantalla(front);

    }








    //===============================================================================================================

    private static class Graficador
    {


        public void dibujarProceso(JPanel lienzo)
        {

            Graphics g = lienzo.getGraphics();
            g.setColor(Color.BLUE);
            g.drawLine(0, 50, 100, 50);   // Línea superior
            g.drawLine(0, 50, 0, 10);     // Línea izquierda
            g.drawLine(100, 50, 100, 10); // Línea derecha
            g.drawLine(0, 10, 100, 10);   // Línea inferior

        }


        public void dibujarImpresion(Front front)
        {

            JPanel lienzo = front.getPanel1();

            QuadCurve2D curve = new QuadCurve2D.Double();
            Graphics2D lapiz = (Graphics2D) lienzo.getGraphics();
            lapiz.setColor(Color.CYAN);

            lapiz.drawLine(100,100,200,100);
            lapiz.drawLine(200,100,200,140);
            lapiz.drawLine(100,100,100,150);
            curve.setCurve(100,150,125,165,150,150);
            lapiz.draw(curve);
            curve.setCurve(150,150,175,135,200,140);
            lapiz.draw(curve);

        }


        public void dibujarInicio(Front front)
        {

            QuadCurve2D curve = new QuadCurve2D.Double();
            Graphics2D lapiz = (Graphics2D) front.getGraphics();

            lapiz.setColor(Color.MAGENTA);
            lapiz.drawLine(50,200,150,200);
            lapiz.drawLine(50,250,150,250);
            curve.setCurve(50,200,30,205,30,225);
            lapiz.draw(curve);
            curve.setCurve(50,250,30,245,30,225);
            lapiz.draw(curve);
            curve.setCurve(150,200,170,205,170,225);
            lapiz.draw(curve);
            curve.setCurve(150,250,170,245,170,225);
            lapiz.draw(curve);

        }

        public void dibujarFin(Front front)
        {

            QuadCurve2D curve = new QuadCurve2D.Double();
            Graphics2D lapiz = (Graphics2D) front.getGraphics();

            lapiz.setColor(Color.BLACK);
            lapiz.drawLine(50,200,150,200);
            lapiz.drawLine(50,250,150,250);
            curve.setCurve(50,200,30,205,30,225);
            lapiz.draw(curve);
            curve.setCurve(50,250,30,245,30,225);
            lapiz.draw(curve);
            curve.setCurve(150,200,170,205,170,225);
            lapiz.draw(curve);
            curve.setCurve(150,250,170,245,170,225);
            lapiz.draw(curve);

        }

        public void dibujarDecision(Front front)
        {

            Graphics g = front.getGraphics();
            g.setColor(Color.RED);
            g.drawLine(100,70,150, 100);
            g.drawLine(150,100, 100, 130);
            g.drawLine(100, 130, 50,100);
            g.drawLine(50, 100, 100, 70);

        }


        public void dibujarEntradaSalida(Front front)
        {

            Graphics g = front.getGraphics();
            g.setColor(Color.orange);
            g.drawLine(125,150,250, 150);
            g.drawLine(250,150,225,200);
            g.drawLine(225,200,100,200);
            g.drawLine(100,200,125,150);

        }


        public void limpiarPantalla(Front front)
        {

            JPanel lienzo = front.getPanel1();

            lienzo.removeAll();
            lienzo.repaint();

        }

    }
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
