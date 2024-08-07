package Dibujos.PanelesMovibles.For;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DibujoFor;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoForInterno extends JPanel {

    private ForInterno derecha;
    private ForInterno izquierda;
    private String     texto;
    private ForVacio2  datos;
    private DibujoFor dibujoFor;


    public DibujoForInterno(String _texto, int _v_inicial, int _v_final, int _incremento, List <Object> _variables, DibujoFor _dibujoFor){
        this.dibujoFor = _dibujoFor;
        this.texto     = _texto;
        this.derecha   = new ForInterno();
        this.izquierda = new ForInterno();
        this.datos     = new ForVacio2(_texto,null,null,_incremento,_v_inicial,_v_final,null,null,_variables,dibujoFor);

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

    public JPanel getIzquierda()
    {
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

    public void setIntervalo(int[] vals)
    {
        this.datos.setValorinicial(vals[0]);
        this.datos.setIncremento(vals[1]);
        this.datos.setValorFinal(vals[2]);
    }


    public String getTexto()
    {
        return this.datos.getTexto();
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

    public class ForInterno extends JPanel{
        private final List<PanelPersonalizado> listaFiguras;
        public double zoomFactor = 1.0;

        ForInterno(){
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

            g2d.drawLine(0, panelHeight / 2, centro_x, panelHeight / 2);

            if (this == izquierda) {
                g2d.drawLine(centro_x, 0, centro_x, panelHeight);
            }
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
