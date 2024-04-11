import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Front extends JFrame {

    private JPanel panel1;
    private JPanel panel;
    private JButton etapaDelProcesoButton;
    private JButton decisionButton;
    private JButton entradaButton;
    private JButton salidaButton;
    private JButton documentoButton;
    private JButton limpiarButton;
    private JScrollPane scroll;
    private JPanel columna;
    private List <PanelPersonalizado> listaPaneles;


    public Front(Controlador controlador)
    {

        setContentPane(panel);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graficador Interactivo de Diagramas de flujo");
        setVisible(true);
        listaPaneles = new ArrayList<>();
        this.columna = new JPanel();

        controlador.initFront(Front.this,listaPaneles,this.scroll,this.panel);




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
                listaPaneles = new ArrayList<>();
                controlador.initFront(Front.this,listaPaneles,scroll,panel);

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

                controlador.crearEntreda(Front.this);

            }

        });



        //Dibujar Paralelogramo /__/
        salidaButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearSalida(Front.this);

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


    }

    public JPanel getPanel1()
    {

        return panel1;

    }

    public JPanel getColumna()
    {

        return columna;

    }
}
