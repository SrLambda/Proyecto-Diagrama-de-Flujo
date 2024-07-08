package Dibujos.PanelesMovibles.For;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DibujoForInicio extends PanelPersonalizado {
    private DibujoForInterno interno;
    private List<PanelPersonalizado> panelesCiclo;
    public DibujoForInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoForInterno _interno,
                           GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        this.interno = _interno;
        panelesCiclo = lista;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int centro_x = panelWidth/2;                                         // Centro horizontal
        int centro_y = panelHeight/2;                                        // Centro vertical

        int cuarto = panelWidth/4;

        // Dibujar flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x,0,centro_x,centro_y);
        g2d.drawLine(centro_x-50,centro_y,centro_x-60,centro_y+10);   //Flecha
        g2d.drawLine(centro_x-50,centro_y,centro_x-60,centro_y-10);
        g2d.setColor(Color.BLACK);
        g2d.drawLine(cuarto,centro_y,cuarto*3,centro_y); //Linea completa
        g2d.drawLine(cuarto,centro_y,cuarto,panelHeight);
        g2d.drawLine(cuarto*3,centro_y,cuarto*3,panelHeight);
        g2d.drawLine(cuarto*3,panelHeight,cuarto*3-10,panelHeight-10); //Flecha
        g2d.drawLine(cuarto*3,panelHeight,cuarto*3+10,panelHeight-10);
    }
}
