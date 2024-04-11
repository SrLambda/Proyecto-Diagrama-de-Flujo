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
    private List <PanelPersonalizado> listaPaneles;
    private JButton EliminarFiguraButton;

    public Front(Controlador controlador)
    {
        setContentPane(panel);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graficador Interactivo de Diagramas de flujo");
        setVisible(true);
        listaPaneles = new ArrayList<>();
        this.columna = new JPanel();
        controlador.initFront(Front.this,listaPaneles, panel);


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
                int bandera = 0;
                while(bandera < listaPaneles.size()){
                    System.out.println("Posicion"+bandera+": Eje Y:"+listaPaneles.get(bandera).getY());
                    bandera++;
                }
            }
        });

        //Dibujar Rectangulo curvo (__)
        inicioButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controlador.crearInicio(Front.this);
            }
        });

        FinButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controlador.crearFin(Front.this);
            }
        });
    }

    // Método para obtener la figura clicada en las coordenadas dadas
    private PanelPersonalizado obtenerFiguraClicada(int x, int y) {
        for (PanelPersonalizado figura : listaPaneles) {
            if (figura.getBounds().contains(x, y)) {
                return figura;
            }
        }
        return null; // No se encontró ninguna figura en las coordenadas dadas
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
