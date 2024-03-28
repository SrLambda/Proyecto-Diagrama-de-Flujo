import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.QuadCurve2D;

public class Front extends JFrame {

    private JPanel panel1;
    private JPanel panel;
    private JButton inicioFinButton;
    private JButton lineaDeFlujoButton;
    private JButton etapaDelProcesoButton;
    private JButton decisionButton;
    private JButton entradaSalidaButton;
    private JButton documentoButton;
    private JButton limpiarButton;


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

                controlador.crearDecision(Front.this);

            }

        });


        limpiarButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {

                controlador.limpiarPantalla(Front.this);
            }

        });


        entradaSalidaButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

            }

        });


        //Dibujar Flechita ⇾ | Esta función debe cambiar proximamente

        lineaDeFlujoButton.addActionListener(new ActionListener()
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

        });


        //Dibujar Paralelogramo /__/
        entradaSalidaButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearEntredaSalida(Front.this);

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
        inicioFinButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearInicioFin(Front.this);

            }

        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
