import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class Controlador {
    private static Controlador instancia;
    private final Graficador graficador;



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


    public void crearProceso(Front front)
    {

        this.graficador.dibujarProceso(front);

    }


    public void crearImpresion(Front front)
    {

        this.graficador.dibujarImpresion(front);

    }


    public void limpiarPantalla(Front front)
    {

        this.graficador.limpiarPantalla(front);

    }





    //====================================================

    private static class Graficador
    {


        public void dibujarProceso(Front front)
        {

            JPanel lienzo = front.getPanel1();

            Graphics g = lienzo.getGraphics();
            g.setColor(Color.BLUE);
            g.drawLine(100, 100, 200, 100);
            g.drawLine(100, 100, 100, 40);
            g.drawLine(200, 100, 200, 40);
            g.drawLine(100, 40, 200, 40);

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


        public void limpiarPantalla(Front front)
        {

            JPanel lienzo = front.getPanel1();

            lienzo.removeAll();
            lienzo.repaint();

        }

    }
}
