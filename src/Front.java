import Dibujos.PanelPersonalizado;
import Dibujos.Validador.Validador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Front extends JFrame {

    private      JPanel panel1;
    private      JPanel panel;
    private      JPanel pseudocodio;
    private     JButton etapaDelProcesoButton;
    private     JButton decisionButton;
    private     JButton entradaButton;
    private     JButton salidaButton;
    private     JButton documentoButton;
    private     JButton limpiarButton;
    private     JButton whileButton;
    private     JButton pseudoCodigoButton;
    private     JButton forButton;
    private     JButton doWhileButton;
    private     JScrollPane scroll;
    // Crear los botones
    private     JButton zoomin;
    private     JButton zoomout;
    private List <PanelPersonalizado> listaPaneles;
    private double zoomFactor = 1.0;

    public Front(Controlador controlador)
    {

        setContentPane(panel);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graficador Interactivo de Diagramas de flujo");
        setVisible(true);

        listaPaneles = new ArrayList<>();

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


        pseudoCodigoButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.pseudoCodigo(Front.this);

            }


        });

        forButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.crearPanel(Front.this,"for");
            }
        });

        whileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.crearPanel(Front.this,"while");
            }
        });

        doWhileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.crearPanel(Front.this,"do-while");
            }
        });

        zoomin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomIn();
            }
        });

        zoomout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomOut();
            }
        });
    }

    public JPanel getPanel1()
    {
        return  this.panel1;
    }

    public JPanel getPseudocodio()
    {
        return this.pseudocodio;
    }

    private void zoomIn() {
        zoomFactor *= 1.01; // Aumentar el zoom en un 10%
        applyZoom();
    }

    private void zoomOut() {
        zoomFactor /= 1.01; // Reducir el zoom en un 10%
        applyZoom();
    }

    private void applyZoom() {
        for (PanelPersonalizado panel : listaPaneles) {
            panel.setZoomFactor(zoomFactor); // Aplicar el factor de zoom a cada panel personalizado
        }
    }
}
