package Dibujos;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import Dibujos.Validador.Validador;
import Dibujos.Validador.ValidadorCadena;
import Dibujos.Validador.ValidadorDouble;
import Dibujos.Validador.ValidadorEntero;
import Dibujos.Ventana.VentanaEmergente;

public abstract class PanelPersonalizado extends JPanel
{

    public String texto;
    protected List <PanelPersonalizado> listaFiguras;
    protected int posOriginal = -1;
    protected JPanel contenedor;
    public boolean habilitado = true;
    protected int posicion = -1;
    protected VentanaEmergente ventanaEmergente;

    protected int[] anchoAlto;

    protected static Font textoFont;
    protected static Float zoom;
    protected Integer witdh;
    protected Integer height;

    protected Validador validarEntero;
    protected Validador validarDouble;
    protected Validador validarCadena;
    protected GridBagConstraints restriciones;
    protected List <String> variables;
    protected Map<String, Object> variables2;

    public double zoomFactor = 1.0;

    public PanelPersonalizado(String _texto, List <PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente)
    {
        this.texto = _texto;
        this.listaFiguras = lista;
        this.contenedor = _contenedor;
        this.ventanaEmergente = _ventanaEmergente;
        this.restriciones = _restriciones;
        this.validarEntero = new ValidadorEntero();
        this.validarDouble = new ValidadorDouble();
        this.validarCadena = new ValidadorCadena();
        this.variables = new ArrayList<>();
        setPreferredSize(new Dimension(750, 200));

        if (null == zoom){
            zoom = 1.0f;
        }

        try {
            InputStream is = PanelPersonalizado.class.getResourceAsStream("/fonts/GohuFont14NerdFontMono-Regular.ttf");
            assert is != null;
            textoFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(14f);
        }
        catch (IOException | FontFormatException e)
        {
            e.printStackTrace();
        }



        this.anchoAlto = this.getAnchoAlto();
        setPreferredSize(new Dimension(200, 100));

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

        this.texto = nuevoTexto;
        repaint();// Redibujar la figura con el nuevo texto
        revalidate();

    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        revalidate(); // Revalidar el layout
        repaint();   // Repintar el panel con el nuevo zoom
    }

    //para los paneles de las figuras
    @Override
    public Dimension getPreferredSize() {
        // Obtener el tamaño original del panel y aplicarle el factor de zoom
        Integer Width = (int) (200 * zoomFactor);  // Ancho original del panel
        Integer Height = (int) (100 * zoomFactor); // Alto original del panel
        return new Dimension((int) (Width * zoomFactor * 2.5), (int) (Height * zoomFactor));
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
            ventanaEmergente.actualizarCompnentes();
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

    public String validar(boolean evidencia, String opcion, String entrada){
        while(true){
            if(evidencia){
                return entrada;
            }else{
                this.texto = entrada;
                entrada = JOptionPane.showInputDialog(null, "Variable invalida", this.texto);
                this.texto = entrada;
                if(opcion.equals("Cadena")){
                    evidencia = validarCadena.validar(entrada);
                } else if (opcion.equals("Entero")){
                    evidencia = validarEntero.validar(entrada);
                } else if (opcion.equals("Double")){
                    evidencia = validarDouble.validar(entrada);
                }
                if(evidencia){
                    return entrada;
                }
            }
        }
    }



    private int[] getAnchoAlto(){

        JLabel tempLabel = new JLabel();
        FontMetrics metrics = tempLabel.getFontMetrics(this.textoFont);

        int width  = metrics.stringWidth(this.texto);
        int height = metrics.getHeight();

        if(width < 105) //  ANCHO DE LA CADENA 'WWWWW'
        {

            width = 105;

        }

        int[] valores = new int[2];

        valores[0] = width;
        valores[1] = height;

        return valores;
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


