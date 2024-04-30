package Dibujos;
import org.w3c.dom.ls.LSOutput;
import javax.swing.*;
import java.awt.*;
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




    public PanelPersonalizado(String _texto, List <PanelPersonalizado> lista, JPanel _contenedor)
    {
        this.texto = _texto;
        this.listaFiguras = lista;
        this.contenedor = _contenedor;
        setPreferredSize(new Dimension(250, 100));

    }

    public int colisiones()
    {
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
        repaint(); // Redibujar la figura con el nuevo texto

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
                ((JPanel) parent).remove(this);
            }

            // Eliminar esta figura de la lista de figuras
            listaFiguras.remove(indice);

            // Reorganizar las posiciones visuales de las figuras restantes en el panel principal
            for (int i = indice; i < listaFiguras.size(); i++)
            {
                PanelPersonalizado panel = listaFiguras.get(i);
                panel.setLocation(0, i * panel.getHeight());
            }

            parent.repaint();
        }
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