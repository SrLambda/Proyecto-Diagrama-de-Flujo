package Dibujos.PanelesMovibles.Decision;

import Dibujos.FactoryPanel;
import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEmergenteDecision {

    private String seleccion;
    private boolean verdadero;


    VentanaEmergenteDecision(JPanel verdad, JPanel falso, List<PanelPersonalizado> l_verdad, List<PanelPersonalizado> l_falso){
        String[] opciones = {"","Entrada", "Salida", "Proceso", "Documento", "Decision"};

        // Crear un JComboBox con las opciones
        JComboBox<String> comboBox = new JComboBox<>(opciones);

        // Crear los botones
        JButton botonVerdadero = new JButton("Verdadero");
        JButton botonFalso = new JButton("Falso");
        JButton botonCerrar = new JButton("Eliminar");

        // Crear el panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alinear los botones a la derecha
        panelBotones.add(botonVerdadero);
        panelBotones.add(botonFalso);
        panelBotones.add(botonCerrar);

        // Agregar un ActionListener al botón de cerrar
        botonCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                ((Window) SwingUtilities.getRoot(panelBotones)).dispose();
            }
        });
        botonVerdadero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                seleccion = (String) comboBox.getSelectedItem();
                verdadero = true;
                ((Window) SwingUtilities.getRoot(panelBotones)).dispose();
            }
        });
        botonFalso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                seleccion = (String) comboBox.getSelectedItem();
                verdadero = false;
                ((Window) SwingUtilities.getRoot(panelBotones)).dispose();
            }
        });

        // Crear un panel para el contenido de la ventana emergente
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());
        panelContenido.add(comboBox, BorderLayout.CENTER);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);

        // Mostrar la ventana emergente
        int resultado = JOptionPane.showOptionDialog(null, panelContenido, "Menu Decision",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);

        if(!seleccion.equalsIgnoreCase(""))
        {
            FactoryPanel factory = new FactoryPanel();
            if(verdadero)
            {
                PanelPersonalizado nuevo = factory.crearPanel(seleccion,entradaDeTexto(),l_verdad,verdad);
                l_verdad.add(nuevo);
                verdad.add(nuevo);
                verdad.revalidate();
            }
            else
            {
                PanelPersonalizado nuevo = factory.crearPanel(seleccion,entradaDeTexto(),l_falso,falso);
                l_falso.add(nuevo);
                falso.add(nuevo);
                falso.revalidate();
            }
        }

    }

    public static String entradaDeTexto() {

        JTextField textField = new JTextField();

        Object[] message = {
                "Ingrese datos:", textField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Datos", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION)
        {
            return textField.getText();

        }
        else
        {
            return "----";

        }
    }
}



