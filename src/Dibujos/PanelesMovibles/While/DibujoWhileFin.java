package Dibujos.PanelesMovibles.While;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DibujoWhileFin extends PanelPersonalizado {

    public DibujoWhileFin(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                          VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto,lista,_contenedor,_restriciones,_ventanaEmergente,_variables);
        setPreferredSize(new Dimension(600, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int centro_x = panelWidth/2;        // Centro horizontal
        int centro_y = panelHeight/2;     // Centro vertical

        int cuarto = panelWidth/4;

        // Dibujar flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x,0,centro_x,panelHeight/2);    // Linea vertical central inferior
        g2d.drawLine( 100,0,100, centro_y);     // Linea vertical izquierda --------------
        g2d.drawLine(panelWidth-100, 0,panelWidth-100, (int) (centro_y+centro_y*0.5));   // Linea vertical derecha
        g2d.drawLine(100, centro_y,centro_x, centro_y); // Linea horizontal izquierda
        g2d.drawLine(centro_x, (int) (panelHeight*0.75),panelWidth-100, (int) (panelHeight*0.75)); // Linea horizontal derecha
        g2d.drawLine(centro_x, (int) (panelHeight*0.75),centro_x,panelHeight); // Linea inferior
    }
}
