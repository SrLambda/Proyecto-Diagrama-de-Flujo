package Dibujos.PanelesMovibles.DoWhile;
import Dibujos.PanelPersonalizado;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static java.awt.Color.*;

public class DibujoDoWhileInterno extends JPanel {
    private DoWhileInterno verdadero1;
    private DoWhileInterno verdadero2;

    public DibujoDoWhileInterno() {
        this.verdadero1 = new DoWhileInterno();
        this.verdadero2 = new DoWhileInterno();

        this.setLayout(new BoxLayout(DibujoDoWhileInterno.this, BoxLayout.X_AXIS));

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.verdadero1.setLayout(new BoxLayout(this.verdadero1, BoxLayout.Y_AXIS));
        this.verdadero1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero1.add(new DoWhileVacio("izquierda",null,null,null,null,null));

        this.verdadero2.setLayout(new BoxLayout(this.verdadero2, BoxLayout.Y_AXIS));
        this.verdadero2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero2.add(new DoWhileVacio("null",null,this,null,null,null));

        JPanel espacio = new JPanel();
        espacio.setPreferredSize(new Dimension(200, 100));
        espacio.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        this.add(verdadero1);
        this.add(verdadero2);
        this.add(espacio);
    }

    public JPanel getVerdadero2() {
        return verdadero2;
    }

    public java.util.List<PanelPersonalizado> getListaVerdadera2()
    {
        return verdadero2.getListaFiguras();
    }

    public void ajustarSize()
    {

        int alturaVer = 0;
        int alturaFal = 0;

        int anchoVer  = 0;
        int anchoFal  = 0;

        JPanel panelVer = this.getVerdadero2();

        for (JPanel componete: this.getListaVerdadera2())
        {

            alturaVer += (int) componete.getPreferredSize().getHeight();
            anchoVer   = Math.max( anchoVer , (int) componete.getPreferredSize().getWidth() );

        }


        int altura = Math.max(alturaVer, alturaFal);
        int ancho = Math.max(anchoVer,anchoFal);

        Dimension size = new Dimension(ancho,altura);

        panelVer.setPreferredSize(size);

        panelVer.revalidate();


        Dimension sizeG = new Dimension(ancho * 2 , altura );

        this.setPreferredSize(sizeG);
        this.revalidate();
    }


    public class DoWhileInterno extends JPanel {
        private final List<PanelPersonalizado> listaFiguras;
        public double zoomFactor = 1.0;

        DoWhileInterno() {
            listaFiguras = new ArrayList<>();
        }

        public List<PanelPersonalizado> getListaFiguras()
        {
            return listaFiguras;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.scale(zoomFactor, zoomFactor);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int centro_x = panelWidth / 2; // Centro horizontal

            g2d.setColor(BLACK);

            // Para verdadero2: dibujar línea vertical en el centro del panel
            if (this == verdadero2) {
                g2d.drawLine(centro_x, 0, centro_x, panelHeight);
            }
        }
    }
}


