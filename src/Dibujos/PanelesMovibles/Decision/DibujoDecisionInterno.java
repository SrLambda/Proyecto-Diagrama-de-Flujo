package Dibujos.PanelesMovibles.Decision;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoDecisionInterno extends JPanel {

    private final DesicionInterna verdadero;
    private final DesicionInterna falso;

    public DibujoDecisionInterno()
    {
        setPreferredSize(new Dimension(200, 400));
        this.setLayout(new BoxLayout(DibujoDecisionInterno.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.verdadero = new DesicionInterna();
        this.falso = new DesicionInterna();

        this.verdadero.setLayout(new BoxLayout(this.verdadero, BoxLayout.Y_AXIS));
        this.verdadero.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero.add(new DesicionVacia(null,null,null,null));


        this.falso.setLayout(new BoxLayout(this.falso, BoxLayout.Y_AXIS));
        this.falso.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.falso.add(new DesicionVacia(null,null,null,null));

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

    public void ajustarSize()
    {

        int alturaVer = 0;
        int alturaFal = 0;

        int anchoVer  = 0;
        int anchoFal  = 0;

        JPanel panelVer = this.getVerdadero();
        JPanel panelFal = this.getFalso();



        for (JPanel componete: this.getListaVerdadera())
        {

            alturaVer += (int) componete.getPreferredSize().getHeight();
            anchoVer   = Math.max( anchoVer , (int) componete.getPreferredSize().getWidth() );

        }


        for (JPanel componete: this.getListaFalsa())
        {

            alturaFal += (int) componete.getPreferredSize().getHeight();
            anchoFal   = Math.max( anchoFal , (int) componete.getPreferredSize().getWidth() );

        }



        int altura = Math.max(alturaVer, alturaFal);
        int ancho = Math.max(anchoVer,anchoFal);

        Dimension size = new Dimension(ancho,altura);

        panelVer.setPreferredSize(size);
        panelFal.setPreferredSize(size);

        panelVer.revalidate();
        panelFal.revalidate();


        Dimension sizeG = new Dimension(ancho * 2 , altura );

        this.setPreferredSize(sizeG);
        this.revalidate();
    }




    public class DesicionInterna extends JPanel {
        private final List<PanelPersonalizado> listaFiguras;

        DesicionInterna() {
            listaFiguras = new ArrayList<>();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);

            int ancho = this.getWidth();
            int alto = this.getHeight();

            g.drawLine(ancho / 2, 0, ancho / 2, alto);
        }



        public List<PanelPersonalizado> getListaFiguras()
        {
            return listaFiguras;
        }
    }

}

