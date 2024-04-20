import Dibujos.PanelPersonalizado;
import Mapa.Mapa;

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
    private JScrollPane scroll;
    private JPanel columna;
    private List <PanelPersonalizado> listaPaneles;
    private Mapa mapa = new Mapa();


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

                controlador.crearPanel(Front.this,"proceso",mapa);
            }

        });


        //boton para dibujar un rombo
        decisionButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearPanel(Front.this,"decision",mapa);

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


        //Dibujar Paralelogramo /__/
        entradaButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearPanel(Front.this,"entrada",mapa);

            }

        });


        //Dibujar Paralelogramo /__/
        salidaButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearPanel(Front.this,"salida",mapa);

            }

        });


        //Dibujar Impresion /_-/
        documentoButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.crearPanel(Front.this,"documento",mapa);

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
