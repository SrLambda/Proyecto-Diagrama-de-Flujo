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
                aumentarZoom(panel1);
            }
        });

        zoomout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reducirZoom(panel1);
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

    // Método para aumentar el zoom del panel
    private void aumentarZoom(JPanel panel) {
        double factor = 1.1; // Factor de incremento de zoom
        aumentarZoom(panel, factor);
    }

    // Método para reducir el zoom del panel
    private void reducirZoom(JPanel panel) {
        double factor = 0.9; // Factor de decremento de zoom
        aumentarZoom(panel, factor);
    }

    // Método para ajustar el zoom del panel según el factor dado
    private void aumentarZoom(JPanel panel, double factor) {
        if (panel != null) {
            // Obtener el tamaño actual del panel
            Dimension dimension = panel.getSize();
            // Calcular el nuevo tamaño considerando el factor de zoom
            int newWidth = (int) (dimension.width * factor);
            int newHeight = (int) (dimension.height * factor);
            // Establecer el nuevo tamaño del panel
            panel.setPreferredSize(new Dimension(newWidth, newHeight));
            // Repintar el panel para aplicar los cambios de tamaño
            panel.revalidate();
            panel.repaint();
        }
    }
}
