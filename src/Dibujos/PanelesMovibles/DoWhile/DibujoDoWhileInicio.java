package Dibujos.PanelesMovibles.DoWhile;

import Dibujos.PanelPersonalizado;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DibujoDoWhileInicio extends PanelPersonalizado {
    private DibujoDoWhileInterno interno;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);

    public DibujoDoWhileInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoDoWhileInterno _interno,GridBagConstraints _restriciones) {
        super(texto, lista, _contenedor,_restriciones);
        this.interno = _interno;
        setPreferredSize(new Dimension(200, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int centro_x = panelWidth / 2;     // Centro horizontal
        int centro_y = panelHeight / 2;    // Centro vertical

        int cuarto = panelWidth / 4;

        g.drawLine(centro_x/3,centro_y,centro_x/3, centro_y*3);     // Linea vertical izquierda
        g.drawLine(centro_x,0,centro_x, panelHeight);     // Linea vertical central
        g.drawLine(centro_x, centro_y,centro_x/3, centro_y); // Linea horizontal izquierda
    }
}

