package Dibujos.PanelesMovibles.While;

import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionInterno;
import Dibujos.PanelesMovibles.For.ForVacio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoWhileInterno extends JPanel {
    private WhileInterna verdadero1;
    private WhileInterna verdadero2;
    private WhileInterna falso;

    public DibujoWhileInterno()
    {
        this.verdadero1 = verdadero1;
        this.verdadero2 = verdadero2;
        this.falso = falso;
        setPreferredSize(new Dimension(600, 400));
        this.setLayout(new BoxLayout(DibujoWhileInterno.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.verdadero1 = new WhileInterna();
        this.verdadero2 = new WhileInterna();
        this.falso = new WhileInterna();

        this.verdadero1.setLayout(new BoxLayout(this.verdadero1, BoxLayout.Y_AXIS));
        this.verdadero1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero1.add(new WhileVacio(null,null,null,null));

        this.verdadero2.setLayout(new BoxLayout(this.verdadero2, BoxLayout.Y_AXIS));
        this.verdadero2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero2.add(new WhileVacio(null,null,null,null));

        this.falso.setLayout(new BoxLayout(this.falso, BoxLayout.Y_AXIS));
        this.falso.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.falso.add(new WhileVacio(null,null,null,null));

        this.add(verdadero1);
        this.add(verdadero2);
        this.add(falso);
    }

    public JPanel getVerdadero2() {
        return verdadero2;
    }

    public List<PanelPersonalizado> getListaVerdadera2()
    {
        return verdadero2.getListaFiguras();
    }

    public void ajustarSize()
    {

        int altura = 0;
        int ancho  = 0;

        JPanel panel = this.getVerdadero2();



        for (JPanel componete: this.getListaVerdadera2())
        {

            altura += (int) componete.getPreferredSize().getHeight();
            ancho   = Math.max( ancho , (int) componete.getPreferredSize().getWidth() );

        }


        Dimension size = new Dimension(ancho,altura);

        panel.setPreferredSize(size);

        panel.revalidate();

        Dimension size_aux = new Dimension(200,altura);

        this.verdadero1.setPreferredSize(size_aux);
        this.falso.setPreferredSize(size_aux);


        Dimension sizeG = new Dimension(ancho + 400 , altura );

        this.setPreferredSize(sizeG);
        this.revalidate();
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

        public void ajustarSize(int ancho,int alto)
        {
            for (JPanel panel : listaFiguras) {

                if(panel instanceof WhileVacio)
                {
                    WhileVacio aux = (WhileVacio) panel;
                    aux.ajustarSize(alto);
                    return;
                }
            }
        }
    }
}
