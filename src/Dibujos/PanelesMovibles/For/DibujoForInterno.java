package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoForInterno extends JPanel {

    private ForInterno verdadero1;
    private ForInterno verdadero2;
    private ForInterno falso;

    public DibujoForInterno(){
        this.verdadero1 = verdadero1;
        this.verdadero2 = verdadero2;
        this.falso = falso;
        setPreferredSize(new Dimension(200, 400));
        //this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // FlowLayout con alineación centrada y espacios 01
        this.setLayout(new BoxLayout(DibujoForInterno.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.verdadero1 = new ForInterno();
        this.verdadero2 = new ForInterno();
        this.falso = new ForInterno();

        this.verdadero1.setLayout(new BoxLayout(this.verdadero1, BoxLayout.Y_AXIS));
        this.verdadero1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero1.add(new ForVacio(null,null,null));

        this.verdadero2.setLayout(new BoxLayout(this.verdadero2, BoxLayout.Y_AXIS));
        this.verdadero2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero2.add(new ForVacio(null,null,null));

        this.falso.setLayout(new BoxLayout(this.falso, BoxLayout.Y_AXIS));
        this.falso.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.falso.add(new ForVacio(null,null,null));

        this.add(verdadero1);
        this.add(verdadero2);
        this.add(falso);
    }

    public JPanel getVerdadero1() {
        return verdadero1;
    }

    public List<PanelPersonalizado> getListaVerdadera1()
    {
        return verdadero1.getListaFiguras();
    }

    public JPanel getVerdadero2() {
        return verdadero2;
    }

    public List<PanelPersonalizado> getListaVerdadera2()
    {
        return verdadero2.getListaFiguras();
    }

    public List<PanelPersonalizado> getListaFalsa()
    {
        return falso.getListaFiguras();
    }

    public JPanel getFalso() {
        return falso;
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
            int centro_x = anchoPanel / 2; // Centro horizontal
            int y1 = (int) ((altoPanel / 4)+altoPanel*0.15);
            g.setColor(Color.BLACK);
            g.drawLine(0, altoPanel / 2, centro_x, altoPanel / 2);

            g.drawLine(centro_x, altoPanel / 2, anchoPanel, altoPanel / 2);
        }
        public List<PanelPersonalizado> getListaFiguras()
        {
            return listaFiguras;
        }
    }
}
