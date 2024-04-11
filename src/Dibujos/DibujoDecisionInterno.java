package Dibujos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoDecisionInterno extends JPanel {

    private JPanel verdadero,falso;

    DibujoDecisionInterno()
    {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.verdadero.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.verdadero.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.falso.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.falso.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.add(verdadero);
        this.add(falso);
    }

    private class DesicionInterna extends JPanel{
        private List<PanelPersonalizado> listaFiguras;
        DesicionInterna(){
            listaFiguras= new ArrayList<>();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);

            int ancho = this.getWidth();
            int alto = this.getHeight();

            g.drawLine(ancho/2,0,ancho/2,alto);
        }
    }
}

