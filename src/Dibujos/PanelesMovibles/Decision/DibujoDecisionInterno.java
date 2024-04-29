package Dibujos.PanelesMovibles.Decision;

import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DibujoDecision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoDecisionInterno extends JPanel {


    private final DesicionInterna verdadero;
    private final DesicionInterna falso;

    public DibujoDecisionInterno()
    {

        this.setLayout(new BoxLayout(DibujoDecisionInterno.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.verdadero = new DesicionInterna();
        this.falso = new DesicionInterna();

        this.verdadero.setLayout(new BoxLayout(this.verdadero, BoxLayout.Y_AXIS));
        this.verdadero.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.verdadero.add(new DesicionVacia(null,null,null));




        this.falso.setLayout(new BoxLayout(this.falso, BoxLayout.Y_AXIS));
        this.falso.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.falso.add(new DesicionVacia(null,null,null));

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

