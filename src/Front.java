import Dibujos.PanelPersonalizado;
import Dibujos.RehacerDeshacer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Front extends JFrame {

    private     JPanel panel1;
    private     JPanel panel;
    private     JPanel pseudocodio;
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
    private     JButton ejecutar;
    private     JButton zoomin;
    private     JButton zoomout;
    private     JButton agregarPSDCButton;
    private     JButton undoButton;
    private     JButton redoButton;
    private     List <PanelPersonalizado> listaPaneles;
    private     double zoomFactor = 1.0;
    private RehacerDeshacer manager;


    public Front(Controlador controlador)
    {

        setContentPane(panel);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graficador Interactivo de Diagramas de flujo");
        setVisible(true);

        listaPaneles = new ArrayList<>();
        manager = RehacerDeshacer.getInstance(listaPaneles);

        controlador.initFront(Front.this,listaPaneles,this.scroll,this.panel1);

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

        agregarPSDCButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e)
            {

                controlador.entradaPorPseudocodigo(Front.this);
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

        ejecutar.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {

                controlador.prueba();

            }

        });

        zoomin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomFactor += 0.03;
                ajustarZoom();
            }
        });

        zoomout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //zoomFactor = Math.max(0.1, zoomFactor - 0.1);
                zoomFactor -= 0.03;
                ajustarZoom();
            }
        });


        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.deshacer();
                manager.mostrarFiguras();
                panel1.revalidate();
                panel1.repaint();
            }
        });

        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //manager.rehacer();
                //manager.mostrarFiguras();
                panel1.revalidate();
                panel1.repaint();
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


    private void ajustarZoom() {
        for (PanelPersonalizado panel : listaPaneles) {
            panel.setZoomFactor(zoomFactor);
        }
        panel1.revalidate();
        panel1.repaint();
    }
}


      /*⢀⣤⣤⣶⠶⠶⣶⣤⣤⡀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⢀⣴⠾⠛⠉⠀⢠⣾⣴⡾⠛⠻⣷⣄⠀⠀⠀⠀⠀
⠀⠀⢶⣶⣶⣿⣁⠀⠀⠀⠀⢸⣿⠏⢀⣤⣶⣌⠻⣦⡀⠀⠀⠀
⠀⠀⣴⡟⠁⢉⣙⣿⣦⡀⠀⢸⡏⣴⠟⢡⣶⣿⣧⡹⣷⡀⠀⠀
⠀⣼⠏⢀⣾⠟⠛⠛⠻⣿⡆⠀⠀⢿⣄⠀⠙⠉⠹⣷⡸⣷⠀⠀
⢠⣿⠀⢸⡿⢿⠇⠀⠀⣾⠇⠀⣀⣈⠻⢷⣤⣤⣤⡾⠃⢹⣇⠀
⢸⣿⠀⢸⣧⣀⣀⣠⣾⢋⣴⢿⣿⡛⠻⣶⣤⣉⠁⠀⠀⠀⣿⠀
⠈⣿⠀⠀⠙⠛⠛⠋⠁⣼⣯⣀⣿⠿⠶⠟⠉⠛⢷⣄⠀⠀⣿⡇
⠀⣿⠀⠀⠀⠀⠀⠀⠀⣿⡏⠉⠁⠀⠀⢀⣴⢶⣄⢻⡇⠀⢸⡇
⠀⢻⣇⠀⠀⠀⠀⠀⢠⡿⢀⣀⢠⣾⠷⣾⣧⡶⠿⠟⠁⠀⣾⡇
⠀⠈⣿⣧⡀⠀⠀⣠⣿⣷⠟⢻⣿⣷⡾⠛⠉⠀⠀⠀⠀⢀⣿⠀
⠀⠀⢹⣿⢻⣦⡀⠉⠛⠛⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⣼⠏⠀
⠀⠀⠀⠛⠀⠈⠻⠷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠟*/