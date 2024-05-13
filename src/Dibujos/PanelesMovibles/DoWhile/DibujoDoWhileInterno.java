package Dibujos.PanelesMovibles.DoWhile;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoDoWhileInterno extends JPanel {
    private WhileInterna verdadero1;
    private WhileInterna verdadero2;
    private WhileInterna falso;

    public DibujoDoWhileInterno()
    {
        this.verdadero1 = verdadero1;
        this.verdadero2 = verdadero2;
        this.falso = falso;
        setPreferredSize(new Dimension(200, 400));
        //this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // FlowLayout con alineación centrada y espacios 01
        this.setLayout(new BoxLayout(DibujoDoWhileInterno.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.verdadero1 = new WhileInterna();
        this.verdadero2 = new WhileInterna();
        this.falso = new WhileInterna();

        this.verdadero1.setLayout(new BoxLayout(this.verdadero1, BoxLayout.Y_AXIS));
        this.verdadero1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero1.add(new DoWhileVacio(null,null,null));

        this.verdadero2.setLayout(new BoxLayout(this.verdadero2, BoxLayout.Y_AXIS));
        this.verdadero2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero2.add(new DoWhileVacio(null,null,null));

        this.falso.setLayout(new BoxLayout(this.falso, BoxLayout.Y_AXIS));
        this.falso.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.falso.add(new DoWhileVacio(null,null,null));

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

    public class WhileInterna extends JPanel {
        private final List<PanelPersonalizado> listaFiguras;

        WhileInterna() {
            listaFiguras = new ArrayList<>();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int centro_x = panelWidth / 2; // Centro horizontal

            g.setColor(Color.BLACK);

            /* Para verdadero1: dibujar línea horizontal que conecta con el panel superior
            g.drawLine(0, panelHeight / 2, centro_x, panelHeight / 2);*/

            // Para verdadero2: dibujar línea vertical en el centro del panel
            if (this == verdadero2) {
                g.drawLine(centro_x, 0, centro_x, panelHeight);
            }

            //Para falso: dibujar línea vertical que conecta con el panel inferior
            if (this == falso) {
                g.drawLine(centro_x, 0, centro_x, panelHeight);
            }
        }

        public void agregarPanel(PanelPersonalizado panel, boolean verdadero) {
            JPanel destino = verdadero ? verdadero2 : falso;
            List<PanelPersonalizado> listaDestino = verdadero ? verdadero2.getListaFiguras() : falso.getListaFiguras();

            if (listaDestino.isEmpty()) {
                destino.removeAll();
            }

            listaDestino.add(panel);
            destino.add(panel);
            destino.revalidate();
        }

        public List<PanelPersonalizado> getListaFiguras()
        {
            return listaFiguras;
        }
    }
}
