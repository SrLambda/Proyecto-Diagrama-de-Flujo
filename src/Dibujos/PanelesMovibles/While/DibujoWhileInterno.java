package Dibujos.PanelesMovibles.While;

import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DibujoWhile;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoWhileInterno extends JPanel {
    private final WhileInterna verdadero;
    private final WhileInterna falso;

    public DibujoWhileInterno()
    {
        setPreferredSize(new Dimension(200, 400));
        this.setLayout(new BoxLayout(DibujoWhileInterno.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.verdadero = new WhileInterna();
        this.falso = new WhileInterna();

        this.verdadero.setLayout(new BoxLayout(this.verdadero, BoxLayout.Y_AXIS));
        this.verdadero.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero.add(new WhileVacio(null,null,null));


        this.falso.setLayout(new BoxLayout(this.falso, BoxLayout.Y_AXIS));
        this.falso.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.falso.add(new WhileVacio(null,null,null));

        this.add(verdadero);
        this.add(falso);
    }

    public JPanel getVerdadero() {
        return verdadero;
    }

    public List<PanelPersonalizado> getListaVerdadera()
    {
        return verdadero.getListaFiguras();
    }

    public List<PanelPersonalizado> getListaFalsa()
    {
        return falso.getListaFiguras();
    }

    public JPanel getFalso() {
        return falso;
    }

    public class WhileInterna extends JPanel {
        private final List<PanelPersonalizado> listaFiguras;

        WhileInterna() {
            listaFiguras = new ArrayList<>();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int centro_x = panelWidth/2;        // Centro horizontal
            int centro_y = panelHeight/2;     // Centro vertical

            g.drawLine(centro_x,panelHeight,centro_x,0);    // Linea vertical central intermedia
        }

        public List<PanelPersonalizado> getListaFiguras()
        {
            return listaFiguras;
        }
    }
}
