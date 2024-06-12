package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ForVacio2 extends PanelPersonalizado{

    private int v_inicial;
    private int v_final;
    private int incremento;


    public ForVacio2(String _texto, List<PanelPersonalizado> lista, JPanel _contenedor, int _incremento , int _v_inicial, int _v_final,GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(_texto, lista, _contenedor,_restriciones,_ventanaEmergente);

        this.v_inicial  = _v_inicial;
        this.incremento = _incremento;
        this.v_final    = _v_final;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    modificarValores();

                }
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        int arcWidth = 100;
        int arcHeight = 100;
        int ancho = this.getWidth();
        int alto = this.getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRoundRect(ancho/4, 0, ancho/2, alto/2, arcWidth, arcHeight);

        g.setColor(Color.BLACK);
        g.drawLine(ancho/2,alto/2,ancho/2,alto);

        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, 30);
        String t_incremento = String.valueOf(incremento);
        String t_inicial = String.valueOf(v_inicial);
        String t_final = String.valueOf(v_final);
        g.drawString(t_inicial, ancho/2-30, 60);
        g.drawString(t_incremento, ancho/2, 60);
        g.drawString(t_final, ancho/2+30, 60);
    }

    public void modificarValores()
    {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Establecer el layout vertical
        JTextField variable = new JTextField(10);
        JTextField _inicial = new JTextField(10);
        JTextField _incremento = new JTextField(10);
        JTextField _final = new JTextField(10);

        panel.add(new JLabel("Variable:"));
        panel.add(variable);
        panel.add(new JLabel("Valor inicial:"));
        panel.add(_inicial);
        panel.add(new JLabel("Incremento:"));
        panel.add(_incremento);
        panel.add(new JLabel("Valor final:"));
        panel.add(_final);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Editar ciclo", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Obtener los datos ingresados por el usuario
            String dato1 = variable.getText();
            String dato2 = _inicial.getText();
            String dato3 = _incremento.getText();
            String dato4 = _final.getText();
            texto = dato1;
            incremento = Integer.parseInt(dato3);
            v_inicial = Integer.parseInt(dato2);
            v_final = Integer.parseInt(dato4);

        } else {
            System.out.println("Operación cancelada.");
        }

    }

    public int getValorInicial()
    {
        return this.v_inicial;
    }

    public int getIncremento()
    {
        return this.incremento;
    }

    public int getValorFinal()
    {
        return this.v_final;
    }

}