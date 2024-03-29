import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.QuadCurve2D;

public class Front extends JFrame {

    private JPanel panel1;
    private JPanel panel;
    private JButton inicioButton;
    private JButton etapaDelProcesoButton;
    private JButton decisionButton;
    private JButton entradaButton;
    private JButton salidaButton;
    private JButton documentoButton;
    private JButton limpiarButton;
    private JButton FinButton;


    public Front(Controlador controlador)
    {

        setContentPane(panel);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graficador Interactivo de Diagramas de flujo");
        setVisible(true);

        //Point2D punto1 = new Point2D.Double(10, 10);
        //Line2D linea1 = new Line2D.Double(50, 50, 150, 150);
        //QuadCurve2D curvaCuadratica1 = new QuadCurve2D.Double(50, 50, 100, 150, 150, 50);


        //botón para dibujar un rectángulo
        etapaDelProcesoButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearProceso(Front.this);
            }

        });


        //boton para dibujar un rombo
        decisionButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                Graphics g = panel1.getGraphics();
                g.setColor(Color.RED);
                g.drawLine(100,70,150, 100);
                g.drawLine(150,100, 100, 130);
                g.drawLine(100, 130, 50,100);
                g.drawLine(50, 100, 100, 70);

            }

        });


        limpiarButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {

                controlador.limpiarPantalla(Front.this);
            }

        });


        /*entradaSalidaButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

            }

        });*/

        //Dibujar Paralelogramo /__/
        entradaButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                Graphics g = panel1.getGraphics();
                g.setColor(Color.orange);
                g.drawLine(125,150,250, 150);
                g.drawLine(250,150,225,200);
                g.drawLine(225,200,100,200);
                g.drawLine(100,200,125,150);

            }

        });

        //Dibujar Flechita -->
        /*lineaDeFlujoButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                Graphics g = panel1.getGraphics();
                g.setColor(Color.BLACK);
                g.drawLine(200,200,200, 100);
                g.drawLine(200,200,225,175);
                g.drawLine(200,200,175,175);

            }

        });*/


        //Dibujar Paralelogramo /__/
        salidaButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                Graphics g = panel1.getGraphics();
                g.setColor(Color.orange);
                g.drawLine(125,150,250, 150);
                g.drawLine(250,150,225,200);
                g.drawLine(225,200,100,200);
                g.drawLine(100,200,125,150);

            }

        });


        //Dibujar Impresion /_-/
        documentoButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearImpresion(Front.this);

            }

        });


        //Dibujar Rectangulo curvo (__)
        inicioButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                QuadCurve2D curve = new QuadCurve2D.Double();
                Graphics2D lapiz = (Graphics2D) panel1.getGraphics();
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

        });

        FinButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                QuadCurve2D curve = new QuadCurve2D.Double();
                Graphics2D lapiz = (Graphics2D) panel1.getGraphics();
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

        });

    }

    public JPanel getPanel1() {
        return panel1;
    }
}
