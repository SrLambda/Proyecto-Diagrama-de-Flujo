package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoForInterno extends JPanel {

    private ForInterno derecha;
    private ForInterno izquierda;
    private String     texto;
    private ForVacio2  datos;


    public DibujoForInterno(String _texto,int _v_inicial, int _v_final, int _incremento){
        this.texto     = _texto;
        this.derecha   = new ForInterno();
        this.izquierda = new ForInterno();
        this.datos     = new ForVacio2(_texto,null,null,_incremento,_v_inicial,_v_final,null,null,null);

        this.setLayout(new BoxLayout(DibujoForInterno.this, BoxLayout.X_AXIS));

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.derecha.setLayout(new BoxLayout(this.derecha, BoxLayout.Y_AXIS));
        this.derecha.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.derecha.add(this.datos);

        this.izquierda.setLayout(new BoxLayout(this.izquierda, BoxLayout.Y_AXIS));
        this.izquierda.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.izquierda.add(new ForVacio(texto,null,null,null,null,null));


        this.add(derecha);
        this.add(izquierda);

    }

    public void modificarValores()
    {
        this.datos.modificarValores();
    }

    public void ajustarSize()
    {

        int altura = 0;
        int ancho  = 0;

        JPanel panel = this.getIzquierda();



        for (JPanel componete: this.getListaIzquierda())
        {

            altura += (int) componete.getPreferredSize().getHeight();
            ancho   = Math.max( ancho , (int) componete.getPreferredSize().getWidth() );

        }


        Dimension size = new Dimension(ancho,altura);

        panel.setPreferredSize(size);
        panel.revalidate();

        this.derecha.setPreferredSize(size);
        this.derecha.ajustarSize(ancho,altura);


        Dimension sizeG = new Dimension(ancho * 2 , altura );

        this.setPreferredSize(sizeG);
        this.revalidate();
    }

    public JPanel getIzquierda() {
        return izquierda;
    }

    public List<PanelPersonalizado> getListaIzquierda()
    {
        return izquierda.getListaFiguras();
    }

    public int[] getIntervalo()
    {

        int[] inter = new int[3];
        inter[0]    = this.datos.getValorInicial();
        inter[1]    = this.datos.getIncremento();
        inter[2]    = this.datos.getValorFinal();

        return inter;

    }


    public String getTexto()
    {
        return this.datos.getTexto();
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

        public void ajustarSize(int ancho,int alto)
        {
            for (JPanel panel : listaFiguras) {

                if(panel instanceof ForVacio)
                {
                    ForVacio aux = (ForVacio) panel;
                    aux.ajustarSize(ancho,alto);
                    return;
                }
            }
        }
    }
}
