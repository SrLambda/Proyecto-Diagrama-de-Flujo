package Dibujos.PanelesMovibles.DoWhile;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DibujoDoWhileInicio extends PanelPersonalizado {

    private DibujoDoWhileInterno interno;

    public DibujoDoWhileInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoDoWhileInterno _interno,
                               GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        this.interno = _interno;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int centro_x = panelWidth / 2;     // Centro horizontal
        int centro_y = panelHeight / 2;    // Centro vertical


        g2d.drawLine(100,centro_y,100, centro_y*3);     // Linea vertical izquierda
        g2d.drawLine(centro_x,0,centro_x, panelHeight);     // Linea vertical central
        g2d.drawLine(centro_x, centro_y,100, centro_y); // Linea horizontal izquierda
    }
}

