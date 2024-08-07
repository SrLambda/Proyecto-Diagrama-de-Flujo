package Dibujos.PanelesMovibles.While;
import Dibujos.PanelPersonalizado;
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
        setPreferredSize(new Dimension(600, 500));
        this.verdadero1 = verdadero1;
        this.verdadero2 = verdadero2;
        this.falso = falso;
        this.setLayout(new BoxLayout(DibujoWhileInterno.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.verdadero1 = new WhileInterna();
        this.verdadero2 = new WhileInterna();
        this.falso = new WhileInterna();

        this.verdadero1.setLayout(new BoxLayout(this.verdadero1, BoxLayout.Y_AXIS));
        this.verdadero1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero1.add(new WhileVacio("izquierda",null,this.verdadero2,null,null,null));

        this.verdadero2.setLayout(new BoxLayout(this.verdadero2, BoxLayout.Y_AXIS));
        this.verdadero2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero2.add(new WhileVacio("null",null,this,null,null,null));

        this.falso.setLayout(new BoxLayout(this.falso, BoxLayout.Y_AXIS));
        this.falso.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.falso.add(new WhileVacio("derecha",null,this.verdadero2,null,null,null));

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

    public class WhileInterna extends JPanel {
        private final List<PanelPersonalizado> listaFiguras;
        public double zoomFactor = 1.0;

        WhileInterna() {
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

            g2d.setColor(Color.BLACK);

            g2d.drawLine(centro_x, 0, centro_x, panelHeight);

            //Para falso: dibujar línea vertical que conecta con el panel inferior
            if (this == falso) {
                g2d.drawLine(centro_x, 0, centro_x, panelHeight);
            }
        }

        public void ajustarSize(int alto)
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
