import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
public class Front extends JFrame {
<<<<<<< HEAD

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
=======
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
    private JPanel columna;
>>>>>>> Edgar
    private List <PanelPersonalizado> listaPaneles;

    public Front(Controlador controlador)
    {
        setContentPane(panel);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graficador Interactivo de Diagramas de flujo");
        setVisible(true);

        listaPaneles = new ArrayList<>();
<<<<<<< HEAD

        controlador.initFront(Front.this,listaPaneles,this.scroll,this.panel1);
=======
        this.columna = new JPanel();
        controlador.initFront(Front.this,listaPaneles, panel);
>>>>>>> Edgar

        //botón para dibujar un rectángulo
        etapaDelProcesoButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
<<<<<<< HEAD

                controlador.crearPanel(Front.this,"proceso");
=======
                controlador.crearProceso(Front.this);
>>>>>>> Edgar
            }

        });


        //boton para dibujar un rombo
        decisionButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
<<<<<<< HEAD

                controlador.crearPanel(Front.this,"decision");

=======
                controlador.crearDecision(Front.this);
>>>>>>> Edgar
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
<<<<<<< HEAD

                controlador.crearPanel(Front.this,"entrada");

=======
                controlador.crearEntreda(Front.this);
>>>>>>> Edgar
            }
        });

<<<<<<< HEAD

=======
>>>>>>> Edgar
        //Dibujar Paralelogramo /__/
        salidaButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
<<<<<<< HEAD

                controlador.crearPanel(Front.this,"salida");

=======
                controlador.crearSalida(Front.this);
>>>>>>> Edgar
            }
        });

        //Dibujar Impresion /_-/
        documentoButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
<<<<<<< HEAD

                controlador.crearPanel(Front.this,"documento");

=======
                controlador.crearImpresion(Front.this);
                int bandera = 0;
                while(bandera < listaPaneles.size()){
                    System.out.println("Posicion"+bandera+": Eje Y:"+listaPaneles.get(bandera).getY());
                    bandera++;
                }
>>>>>>> Edgar
            }
        });

<<<<<<< HEAD

        pseudoCodigoButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.pseudoCodigo(Front.this);

            }


=======
        //Dibujar Rectangulo curvo (__)
        inicioButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controlador.crearInicio(Front.this);
            }
>>>>>>> Edgar
        });

        forButton.addActionListener(new ActionListener() {
            @Override
<<<<<<< HEAD
            public void actionPerformed(ActionEvent e) {
                controlador.crearPanel(Front.this,"for");
=======
            public void actionPerformed(ActionEvent e)
            {
                controlador.crearFin(Front.this);
>>>>>>> Edgar
            }
        });
    }

<<<<<<< HEAD
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
                //
            }
        });

        zoomout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
=======
    // Método para obtener la figura clicada en las coordenadas dadas
    private PanelPersonalizado obtenerFiguraClicada(int x, int y) {
        for (PanelPersonalizado figura : listaPaneles) {
            if (figura.getBounds().contains(x, y)) {
                return figura;
            }
        }
        return null; // No se encontró ninguna figura en las coordenadas dadas
>>>>>>> Edgar
    }

    public JPanel getPanel1()
    {
<<<<<<< HEAD
        return  this.panel1;
=======
        return panel1;
>>>>>>> Edgar
    }

    public JPanel getPseudocodio()
    {
<<<<<<< HEAD
        return this.pseudocodio;
=======
        return columna;
>>>>>>> Edgar
    }
}
