import Dibujos.PanelPersonalizado;

import javax.swing.*;
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
    private JButton whileButton;
    private JScrollPane scroll;
    private JButton forButton;
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



        //botón para dibujar un rectángulo
        etapaDelProcesoButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearPanel(Front.this,"proceso");
            }

        });


        //boton para dibujar un rombo
        decisionButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearPanel(Front.this,"decision");

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


        //Dibujar For
        forButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearPanel(Front.this,"for");

            }
        });


        //Dibujar Paralelogramo /__/
        entradaButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearPanel(Front.this,"entrada");

            }

        });


        //Dibujar Paralelogramo /__/
        salidaButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearPanel(Front.this,"salida");

            }

        });


        //Dibujar Impresion /_-/
        documentoButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearPanel(Front.this,"documento");

            }

        });

        //Dibujar ciclo While
        whileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.crearPanel(Front.this,"while");
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
