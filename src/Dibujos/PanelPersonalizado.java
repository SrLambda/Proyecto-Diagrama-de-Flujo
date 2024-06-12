package Dibujos;
<<<<<<< HEAD
import org.w3c.dom.ls.LSOutput;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
=======
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
>>>>>>> Edgar
import java.util.List;


public abstract class PanelPersonalizado extends JPanel
{
<<<<<<< HEAD

    public String texto;
=======
    protected String texto;
>>>>>>> Edgar
    protected List <PanelPersonalizado> listaFiguras;
    protected int posOriginal = -1;
    protected JPanel contenedor;
    public boolean habilitado = true;
    protected int posicion = -1;

    protected GridBagConstraints restriciones;
    private double escala = 1.0; // Escala inicial


    public PanelPersonalizado(String _texto, List <PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones)
    {
        this.texto = _texto;
        this.listaFiguras = lista;
        this.contenedor = _contenedor;
<<<<<<< HEAD
        this.restriciones = _restriciones;
        setPreferredSize(new Dimension(750, 200));

    }

    public int colisiones()
    {
=======
        setPreferredSize(new Dimension(100, 50));
        this.setBackground(Color.WHITE);
    }


    public void actualizarUbicacion(){
        int indice = listaFiguras.indexOf(this);

        if(indice != -1){
            int nuevaUbicacion = this.getY();
            altura = this.getHeight();
            posOriginal = nuevaUbicacion;
            listaFiguras.get(indice).setLocation(0, nuevaUbicacion);
            //System.out.println("Eje Y dentro de la lista: "+listaFiguras.get(indice).getY());

        }

    }

    public void colisionesVisual() {
    int i=0;
    while(i < listaFiguras.size()){
        PanelPersonalizado panelSiguiente = listaFiguras.get(i);
        if(panelSiguiente != this){
            int ejeY = getY();
            int altura = getHeight();
            int ejeYSiguiente = panelSiguiente.getY();
            int alturaSiguiente = panelSiguiente.getHeight();

            if(ejeY < ejeYSiguiente + alturaSiguiente && ejeY + altura > ejeYSiguiente){
            }
        }
        i++;
    }
    }

    public int colisiones() {
>>>>>>> Edgar
        int i=0;
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

    public void cambiarTexto(String nuevoTexto)
    {
        texto = nuevoTexto;
        repaint();// Redibujar la figura con el nuevo texto
        revalidate();

    }

    // Método para hacer zoom in
    public void hacerZoomIn() {
        escala *= 1.1; // Aumentar la escala en un 10%
        actualizarZoom();
    }

    // Método para hacer zoom out
    public void hacerZoomOut() {
        escala /= 1.1; // Disminuir la escala en un 10%
        actualizarZoom();
    }

<<<<<<< HEAD
    // Método para actualizar la representación gráfica con la nueva escala
    private void actualizarZoom() {
        // Iterar sobre todas las figuras y ajustar sus tamaños y posiciones según la nueva escala
        for (PanelPersonalizado figura : listaFiguras) {
            // Ejemplo de ajuste de tamaño
            figura.setSize((int) (figura.getWidth() * escala), (int) (figura.getHeight() * escala));
            // Ejemplo de ajuste de posición
            figura.setLocation((int) (figura.getX() * escala), (int) (figura.getY() * escala));
        }
        // Repintar el panel para reflejar los cambios
        repaint();
    }

    // Método para eliminar la figura y reorganizar las posiciones
    public void eliminarFigura() {

        // Obtener el índice de esta figura en la lista
        int indice = listaFiguras.indexOf(this);

        if (indice != -1)
        {
            // Eliminar esta figura del panel principal
            Container parent = getParent();
            if (parent instanceof JPanel)
            {
=======
    public void cambiarTexto(String nuevoTexto) {
        texto = nuevoTexto;
        repaint(); // Redibujar la figura con el nuevo texto
    }

    // Método para eliminar la figura y reorganizar las posiciones
    public void eliminarFiguraaa() {
        // Obtener el índice de esta figura en la lista
        int indice = listaFiguras.indexOf(this);
        if (indice != -1) {
            // Eliminar esta figura del panel principal
            Container parent = getParent();
            if (parent instanceof JPanel) {
>>>>>>> Edgar
                ((JPanel) parent).remove(this);
            }

            // Eliminar esta figura de la lista de figuras
            listaFiguras.remove(indice);

            // Reorganizar las posiciones visuales de las figuras restantes en el panel principal
<<<<<<< HEAD
            for (int i = indice; i < listaFiguras.size(); i++)
            {
                PanelPersonalizado panel = listaFiguras.get(i);
                panel.setLocation(0, i * panel.getHeight());
            }

            parent.repaint();
        }
    }

    public String getTexto() {
        return texto;
    }

    public void actualizarContenedor(List<PanelPersonalizado> list,JPanel cont)
    {
        this.listaFiguras = list;
        this.contenedor = cont;
    }

}

/*

⠀⠀⠀⠀⣠⣶⡾⠏⠉⠙⠳⢦⡀⠀⠀⠀⢠⠞⠉⠉⠉⠉⠉⠉⠙⠲⡀⠀
⠀⠀⠀⣴⠿⠏⠀⠀⠀⠀⠀⠀⢳⡀⠀⡏⠀⠀⠀⠀         ⠀⢷
⠀⠀⢠⣟⣋⡀⢀⣀⣀⡀⠀⣀⡀⣧⠀⢸⠀⠀⠀⠀⠀        ⡇
⠀⠀⢸⣯⡭⠁⠸⣛⣟⠆⡴⣻⡲⣿⠀⣸⠀⠀AMA ESTOY  ⡇
⠀⠀⣟⣿⡭⠀⠀⠀⠀⠀⢱⠀⠀⣿⠀⢹⠀⠀⠀  TRISTE   ⡇
⠀⠀⠙⢿⣯⠄⠀⠀⠀⢀⡀⠀⠀⡿⠀⠀⡇⠀⠀⠀        ⠀⡼
⠀⠀⠀⠀⠹⣶⠆⠀⠀⠀⠀⠀⡴⠃⠀⠀⠘⠤⣄⣀⣀⣀⣀⣀⣀⣠⠞⠀
⠀⠀⠀⠀⠀⢸⣷⡦⢤⡤⢤⣞⣁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⢀⣤⣴⣿⣏⠁⠀⠀⠸⣏⢯⣷⣖⣦⡀⠀⠀⠀⠀⠀⠀
⢀⣾⣽⣿⣿⣿⣿⠛⢲⣶⣾⢉⡷⣿⣿⠵⣿⠀⠀⠀⠀⠀⠀
⣼⣿⠍⠉⣿⡭⠉⠙⢺⣇⣼⡏⠀⠀⠀⣄⢸⠀⠀⠀⠀⠀⠀
⣿⣿⣧⣀⣿………⣀⣰⣏⣘⣆⣀⠀⠀

 */


=======
            for (int i = indice; i < listaFiguras.size(); i++) {
                PanelPersonalizado panel = listaFiguras.get(i);
                panel.setLocation(0, i * panel.getHeight());
            }
            parent.repaint();
        }
    }
}
>>>>>>> Edgar
