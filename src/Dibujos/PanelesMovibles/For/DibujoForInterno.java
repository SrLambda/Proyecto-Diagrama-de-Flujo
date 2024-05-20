package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoForInterno extends JPanel {

    private ForInterno derecha;
    private ForInterno izquierda;
    private String texto;



    public DibujoForInterno(String _texto,int _v_inicial, int _v_final, int _incremento){
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.texto = _texto;

        setPreferredSize(new Dimension(200, 400));
        this.setLayout(new BoxLayout(DibujoForInterno.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.derecha = new ForInterno();
        this.izquierda = new ForInterno();


        this.derecha.setLayout(new BoxLayout(this.derecha, BoxLayout.Y_AXIS));
        this.derecha.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.derecha.add(new ForVacio2(_texto,null,null,_incremento,_v_inicial,_v_final,null));

        this.izquierda.setLayout(new BoxLayout(this.izquierda, BoxLayout.Y_AXIS));
        this.izquierda.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.izquierda.add(new ForVacio(texto,null,null,null));


        this.add(derecha);
        this.add(izquierda);

    }

    public JPanel getDerecha() {
        return derecha;
    }

    public List<PanelPersonalizado> getListaVerdadera1()
    {
        return derecha.getListaFiguras();
    }

    public JPanel getIzquierda() {
        return izquierda;
    }

    public List<PanelPersonalizado> getListaIzquierda()
    {
        return izquierda.getListaFiguras();
    }



    public class ForInterno extends JPanel{
        private final List<PanelPersonalizado> listaFiguras;

        ForInterno(){
            listaFiguras = new ArrayList<>();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int centro_x = panelWidth / 2; // Centro horizontal

            g.setColor(Color.BLACK);

            g.drawLine(0, panelHeight / 2, centro_x, panelHeight / 2);

            if (this == izquierda) {
                g.drawLine(centro_x, 0, centro_x, panelHeight);
            }

        }
        public List<PanelPersonalizado> getListaFiguras()
        {
            return listaFiguras;
        }
    }
}
