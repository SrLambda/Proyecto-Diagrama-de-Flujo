package Dibujos.PanelesMovibles.While;

import Dibujos.FactoryPanel;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DibujoDecision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEmergenteWhile {

    private String seleccion;
    private boolean verdadero;

    VentanaEmergenteWhile(JPanel verdad, JPanel falso, List<PanelPersonalizado> l_verdad, List<PanelPersonalizado> l_falso,PanelPersonalizado _contenedor){
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
                int option = JOptionPane.showConfirmDialog(null, "¿Eliminar esta figura?", "Eliminar Figura", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    _contenedor.eliminarFigura();
                }
                seleccion = "";
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
                if(l_verdad.isEmpty()){
                    verdad.removeAll();
                }
                PanelPersonalizado nuevo = factory.crearPanel(seleccion,entradaDeTexto(),l_verdad,verdad,null);
                l_verdad.add(nuevo);
                verdad.add(nuevo);
                ajustarAlto(_alto,l_verdad,seleccion.equalsIgnoreCase("Decision"),nuevo);
                verdad.revalidate();
            }
            else
            {
                if(l_falso.isEmpty()){
                    falso.removeAll();
                }
                PanelPersonalizado nuevo = factory.crearPanel(seleccion,entradaDeTexto(),l_falso,falso,null);
                l_falso.add(nuevo);
                falso.add(nuevo);
                ajustarAlto(_alto,l_falso,seleccion.equalsIgnoreCase("Decision"),nuevo);
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

    private void ajustarAlto(Integer alto,List lista,boolean decision,PanelPersonalizado nuevo) {
        if(decision){
            if(alto < 300 +((lista.size()-1) * 150) + 300 ){
                DibujoDecision aux1= (DibujoDecision) nuevo;
                Integer aux2 = aux1.getAlto();
                alto = 300 +((lista.size()) * 150) + aux2;
            }
        }else{
            if(alto < 300 +(lista.size() * 150)) {
                alto = 300 +(lista.size() * 150);
            }
        }
    }
}