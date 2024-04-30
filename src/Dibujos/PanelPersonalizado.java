package Dibujos;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.List;

public abstract class PanelPersonalizado extends JPanel
{

    protected String texto;
    protected List <PanelPersonalizado> listaFiguras;
    protected int altura;
    protected int posOriginal = -1;
    protected JPanel contenedor;
    public boolean habilitado = true;
    protected int posicion = -1;


    public PanelPersonalizado(String _texto, List <PanelPersonalizado> lista, JPanel _contenedor) {
        this.texto = _texto;
        this.listaFiguras = lista;
        this.contenedor = _contenedor;
        setPreferredSize(new Dimension(250, 50));
    }


    public void actualizarUbicacion(){
        int indice = listaFiguras.indexOf(this);

        if(indice != -1){
            int nuevaUbicacion = this.getY();
            altura = this.getHeight();
            posOriginal = nuevaUbicacion;
            listaFiguras.get(indice).setLocation(0, nuevaUbicacion);
            System.out.println("Eje Y dentro de la lista: "+listaFiguras.get(indice).getY());

        }

    }

    public void colisionesVisual()
    {
        int i=0;
        while(i < listaFiguras.size())
        {

            PanelPersonalizado panelSiguiente = listaFiguras.get(i);

            if(panelSiguiente != this)
            {

                int ejeY = getY();
                int altura = getHeight();
                int ejeYSiguiente = panelSiguiente.getY();
                int alturaSiguiente = panelSiguiente.getHeight();

                if(ejeY < ejeYSiguiente + alturaSiguiente && ejeY + altura > ejeYSiguiente)
                {
                }

            }

            i++;

        }
    }

    public int colisiones()
    {
        int i=0;
        int x = 0;
        int num = listaFiguras.indexOf(this);

        while(i < listaFiguras.size())
        {
            PanelPersonalizado panelColision = listaFiguras.get(i);

            if(panelColision != this)
            {
                int ejeY = this.getY();
                int altura = this.getHeight();
                int ejeYSiguiente = panelColision.getY();
                int alturaSiguiente = panelColision.getHeight();

                if ((ejeY+altura) > ejeYSiguiente && (ejeY+altura) < (ejeYSiguiente + alturaSiguiente))
                {

                    if(listaFiguras.get(num+1).habilitado)
                    {

                        this.posicion = num;
                        return i;

                    }

                }
                else

                {
                    if(ejeY < (ejeYSiguiente+alturaSiguiente) && ejeY > ejeYSiguiente)
                    {

                        if(listaFiguras.get(num-1).habilitado)
                        {

                            this.posicion = num;
                            return i;

                        }

                    }

                }

            }
            i++;
        }
        return -1;
    }

    public void cambiarTexto(String nuevoTexto) {
        texto = nuevoTexto;
        ajustarTamanio();
        repaint(); // Redibujar la figura con el nuevo texto
    }

    public void guardarPosicion(int _posOriginal){
        this.posOriginal = _posOriginal;
        System.out.println("Posicion guardada: "+posOriginal);
    }

    public int posicionOriginal(){
        return this.posOriginal;
    }

    public void actualizarPosicion(){
        this.posOriginal = getY();
    }

    // Método para eliminar la figura y reorganizar las posiciones
    public void eliminarFigura() {
        // Obtener el índice de esta figura en la lista
        int indice = listaFiguras.indexOf(this);
        if (indice != -1) {
            // Eliminar esta figura del panel principal
            Container parent = getParent();
            if (parent instanceof JPanel) {
                ((JPanel) parent).remove(this);
            }

            // Eliminar esta figura de la lista de figuras
            listaFiguras.remove(indice);

            // Reorganizar las posiciones visuales de las figuras restantes en el panel principal
            for (int i = indice; i < listaFiguras.size(); i++) {
                PanelPersonalizado panel = listaFiguras.get(i);
                panel.setLocation(0, i * panel.getHeight());
            }
            parent.repaint();
        }
    }

    // Método para ajustar el tamaño de la figura según el texto
    public void ajustarTamanio() {
        ////
    }
}