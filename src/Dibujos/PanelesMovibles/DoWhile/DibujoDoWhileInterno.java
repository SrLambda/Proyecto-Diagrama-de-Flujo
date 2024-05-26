package Dibujos.PanelesMovibles.DoWhile;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static java.awt.Color.BLACK;

public class DibujoDoWhileInterno extends JPanel {
    private DoWhileInterno verdadero1;
    private DoWhileInterno verdadero2;

    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);

    public DibujoDoWhileInterno() {
        this.verdadero1 = verdadero1;
        this.verdadero2 = verdadero2;

        setPreferredSize(new Dimension(200, 400));
        //this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // FlowLayout con alineación centrada y espacios 01
        this.setLayout(new BoxLayout(DibujoDoWhileInterno.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.verdadero1 = new DoWhileInterno();
        this.verdadero2 = new DoWhileInterno();

        this.verdadero1.setLayout(new BoxLayout(this.verdadero1, BoxLayout.Y_AXIS));
        this.verdadero1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
        this.verdadero1.add(new DoWhileVacio(null,null,null,null));

        this.verdadero2.setLayout(new BoxLayout(this.verdadero2, BoxLayout.Y_AXIS));
        this.verdadero2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 445));
        this.verdadero2.add(new DoWhileVacio(null,null,null,null));

        this.add(verdadero1);
        this.add(verdadero2);
    }
    public JPanel getVerdadero1() {
        return verdadero1;
    }

    public java.util.List<PanelPersonalizado> getListaVerdadera1()
    {
        return verdadero1.getListaFiguras();
    }

    public JPanel getVerdadero2() {
        return verdadero2;
    }

    public java.util.List<PanelPersonalizado> getListaVerdadera2()
    {
        return verdadero2.getListaFiguras();
    }

    public class DoWhileInterno extends JPanel {
        private final List<PanelPersonalizado> listaFiguras;

        DoWhileInterno() {
            listaFiguras = new ArrayList<>();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int centro_x = panelWidth / 2; // Centro horizontal
            int centro_y = panelHeight/2;                                        // Centro vertical

            int cuarto = panelWidth/4;

            g.setColor(BLACK);

            //Para verdadero1: dibujar línea horizontal que conecta con el panel superior
            g.drawLine(0, panelHeight / 2, centro_x, panelHeight / 2);

            // Para verdadero2: dibujar línea vertical en el centro del panel
            if (this == verdadero2) {
                //g.drawLine(centro_x, 0, centro_x, panelHeight);
            }
        }

        /*public void agregarPanel(PanelPersonalizado panel, boolean verdadero) {
            JPanel destino = verdadero ? verdadero2 : falso;
            java.util.List<PanelPersonalizado> listaDestino = verdadero ? verdadero2.getListaFiguras() : falso.getListaFiguras();

            if (listaDestino.isEmpty()) {
                destino.removeAll();
            }

            listaDestino.add(panel);
            destino.add(panel);
            destino.revalidate();
        }*/

        public List<PanelPersonalizado> getListaFiguras()
        {
            return listaFiguras;
        }
    }
}

