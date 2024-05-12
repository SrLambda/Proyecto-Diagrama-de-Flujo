package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoForInterno extends JPanel {

    private ForInterno camino;

    public DibujoForInterno(){
        this.camino = camino;
        setPreferredSize(new Dimension(200, 400));
        //this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // FlowLayout con alineación centrada y espacios 01
        this.setLayout(new BoxLayout(DibujoForInterno.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.camino = new ForInterno();
        this.camino.setLayout(new BoxLayout(this.camino, BoxLayout.Y_AXIS));
        this.camino.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.camino.add(new ForVacio(null,null,null));
        this.add(camino);
    }

    public List<PanelPersonalizado> getListaFalsa()
    {
        return camino.getListaFiguras();
    }

    public JPanel getFalso() {
        return camino;
    }

    public class ForInterno extends JPanel{
        private final List<PanelPersonalizado> listaFiguras;

        ForInterno(){
            listaFiguras = new ArrayList<>();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int anchoPanel = getWidth();
            int altoPanel = getHeight();
            int x1 = (int) (anchoPanel*0.171);
            int x2 = (int) (anchoPanel*0.828);
            int arcWidth = 100;
            int arcHeight = 100;
            int centro_x = anchoPanel / 2; // Centro horizontal
            int y1 = (int) ((altoPanel / 4)+altoPanel*0.15);
            g.setColor(Color.RED);
            g.drawLine(0,0,20,20);
            System.out.println();
            //DIBUJO FOR
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            g2d.drawRoundRect(centro_x-160, y1, (centro_x-60) - (centro_x-160),-30, arcWidth, arcHeight);


            g.drawLine(centro_x, altoPanel / 2, anchoPanel, altoPanel / 2);
        }
        public List<PanelPersonalizado> getListaFiguras()
        {
            return listaFiguras;
        }
    }
}
