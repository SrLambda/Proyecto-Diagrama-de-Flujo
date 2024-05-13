package Dibujos.PanelesMovibles.For;
import Dibujos.FactoryPanel;
import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class VentanaEmergenteFor {
    private String seleccion;
    private boolean verdadero;

    VentanaEmergenteFor(JPanel verdad, List<PanelPersonalizado> l_verdad,PanelPersonalizado _contenedor){
        String[] opciones = {"","Entrada", "Salida", "Proceso", "Documento", "Decision"};

        JComboBox<String> comboBox = new JComboBox<>(opciones);

        JButton botonAnadir = new JButton("Añadir");
        JButton botonEliminar = new JButton("Eliminar");

        // Crear el panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alinear los botones a la derecha
        panelBotones.add(botonAnadir);
        panelBotones.add(botonEliminar);

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Eliminar esta figura?", "Eliminar Figura", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    _contenedor.eliminarFigura();
                }
                seleccion = "";
                ((Window) SwingUtilities.getRoot(panelBotones)).dispose();
            }
        });

        botonAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccion = (String) comboBox.getSelectedItem();
                verdadero = true;
                ((Window) SwingUtilities.getRoot(panelBotones)).dispose();
            }
        });

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());
        panelContenido.add(comboBox, BorderLayout.CENTER);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);

        int resultado = JOptionPane.showOptionDialog(null, panelContenido, "Menu For",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);

        if(!seleccion.equalsIgnoreCase("")){
            FactoryPanel factory = new FactoryPanel();
            if(verdadero){
                if(l_verdad.isEmpty()){
                    verdad.removeAll();
                }
                PanelPersonalizado nuevo = factory.crearPanel(seleccion,entradaDeTexto(),l_verdad,verdad);
                l_verdad.add(nuevo);
                verdad.add(nuevo);
                verdad.revalidate();
            }
            else{
                if(l_verdad.isEmpty()){
                    verdad.removeAll();

                }
                PanelPersonalizado nuevo = factory.crearPanel(seleccion,entradaDeTexto(),l_verdad,verdad);
                l_verdad.add(nuevo);
                verdad.add(nuevo);
                verdad.revalidate();
            }
        }
    }

    public static String entradaDeTexto() {

        JTextField textField = new JTextField();

        Object[] message = {
                "Ingrese datos:", textField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Datos", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION){
            return textField.getText();
        }
        else {
            return "----";
        }
    }
}
