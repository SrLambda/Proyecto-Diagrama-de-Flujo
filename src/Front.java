import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
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
    private JButton pruebas;

    private JButton pseudoCodigoButton;

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

        controlador.initFront(Front.this,listaPaneles,this.scroll,this.panel1);



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
                controlador.initFront(Front.this,listaPaneles,scroll,panel1);

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


        pruebas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.prueba();

            }
        });

        pseudoCodigoButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.pseudoCodigo(Front.this);

            }


        });
    }

    public JPanel getPanel1()
    {
        return  this.panel1;
    }
}
