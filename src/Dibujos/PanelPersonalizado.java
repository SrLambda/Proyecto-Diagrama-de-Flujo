package Dibujos;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import Dibujos.Validador.Validador;
import Dibujos.Validador.ValidadorCadena;
import Dibujos.Validador.ValidadorDouble;
import Dibujos.Validador.ValidadorEntero;
import Dibujos.Ventana.VentanaEmergente;

public abstract class PanelPersonalizado extends JPanel {

    public String texto;
    protected List<PanelPersonalizado> listaFiguras;
    protected int posOriginal = -1;
    protected JPanel contenedor;
    public boolean habilitado = true;
    protected int posicion = -1;
    protected VentanaEmergente ventanaEmergente;

    protected int[] anchoAlto;

    protected static Font textoFont;
    protected static Float zoom;
    protected Validador validarEntero;
    protected Validador validarDouble;
    protected Validador validarCadena;
    protected GridBagConstraints restriciones;
    protected List <Object> variables;
    protected static int indice1 = 0;
    private String tipo;
    private String entrada;

    public double zoomFactor = 1.0;

    public PanelPersonalizado(String _texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                              VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        this.texto = _texto;
        this.listaFiguras = lista;
        this.contenedor = _contenedor;
        this.ventanaEmergente = _ventanaEmergente;
        this.restriciones = _restriciones;
        this.validarEntero = new ValidadorEntero();
        this.validarDouble = new ValidadorDouble();
        this.validarCadena = new ValidadorCadena();
        this.variables = _variables;
        setPreferredSize(new Dimension(750, 200));

        if (null == zoom) {
            zoom = 1.0f;
        }

        try {
            InputStream is = PanelPersonalizado.class.getResourceAsStream("/fonts/GohuFont14NerdFontMono-Regular.ttf");
            assert is != null;
            textoFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(14f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }


        this.anchoAlto = this.getAnchoAlto();
        setPreferredSize(new Dimension(200, 100));

    }

    public String getEntrada() {
        return this.entrada;
    }

    public void setEntrada(String _entrada) {
        this.entrada = _entrada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        revalidate();
        repaint();
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(String _tx){
        this.texto = _tx;
    }


    @Override
    public Dimension getPreferredSize() {
        Dimension originalSize = super.getPreferredSize();
        return new Dimension((int) (originalSize.width * zoomFactor), (int) (originalSize.height * zoomFactor));
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

    public int colisiones() {
        int i = 0;
        int num = listaFiguras.indexOf(this);


        while (i < listaFiguras.size()) {
            PanelPersonalizado panelColision = listaFiguras.get(i);

            if (panelColision != this) {
                int ejeY = this.getY();
                int altura = this.getHeight();
                int ejeYSiguiente = panelColision.getY();
                int alturaSiguiente = panelColision.getHeight();

                if ((ejeY + altura) > ejeYSiguiente && (ejeY + altura) < (ejeYSiguiente + alturaSiguiente)) {

                    if (listaFiguras.get(num + 1).habilitado) {

                        this.posicion = num;
                        return i;

                    }

                } else {
                    if (ejeY < (ejeYSiguiente + alturaSiguiente) && ejeY > ejeYSiguiente) {

                        if (listaFiguras.get(num - 1).habilitado) {

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


    // Método para eliminar la figura y reorganizar las posiciones
    public void eliminarFigura() {
        RehacerDeshacer manager = RehacerDeshacer.getInstance(listaFiguras);
        int indice = listaFiguras.indexOf(this);

        if (indice != -1) {
            // Eliminar esta figura del panel principal
            Container parent = getParent();
            if (parent instanceof JPanel) {
                ((JPanel) parent).remove(this);
            }

            manager.remover(listaFiguras.get(indice));
            listaFiguras.remove(indice);

            // Reorganizar las posiciones visuales de las figuras restantes en el panel principal
            for (int i = indice; i < listaFiguras.size(); i++) {
                PanelPersonalizado panel = listaFiguras.get(i);
                panel.setLocation(0, i * panel.getHeight());
            }

            parent.repaint();
        }
    }

    public String validarMixto(boolean evidencia, String entrada) {
        while (true) {
            if (evidencia) {
                return entrada;
            } else {
                this.texto = entrada;
                entrada = JOptionPane.showInputDialog(null, "Variable invalida", this.texto);
                if(entrada == null){
                    return null;
                }
                this.texto = entrada;
                if (entrada == null || entrada.isEmpty()) {
                    entrada = JOptionPane.showInputDialog(null, "Variable invalida", this.texto);
                    if(entrada == null){
                        return null;
                    }
                } else {
                    try {
                        Integer.parseInt(entrada);
                        evidencia = validarEntero.validar(entrada);
                    } catch (NumberFormatException ex1) {
                        try {
                            Double.parseDouble(entrada);
                            evidencia = validarDouble.validar(entrada);
                        } catch (NumberFormatException ex2) {
                            evidencia = validarCadena.validar(entrada);
                            if(evidencia){
                                evidencia = validarVariableTrueFalse(entrada);
                            }
                        }
                    }
                }
            }
        }
    }

    public String validarVariable(String _variable) {
        boolean bandera = true;
        while(bandera){
            for (int i=0; i < variables.size(); i++) {
                if (variables.get(i).equals(_variable)) {
                    return _variable;
                }
            }
            _variable = JOptionPane.showInputDialog(null, "Variable invalida", _variable);
            _variable = validarMixto(validarCadena.validar(_variable), _variable);
        }
        return _variable;
    }

    public boolean validarVariableTrueFalse(String _entrada) {
        for (int i=0; i < variables.size(); i++) {
            if (variables.get(i).equals(_entrada)) {
                return true;
            }
        }
        return false;
    }

    public String quitarEspacios(String texto){
        if (texto == null || texto.equals("null")) {
            return "null";
        }
        String sinEspacios = texto.replaceAll("\\s", "");
        return sinEspacios;
    }

    public String buscar(String _entrada) {
        try {
            Integer.parseInt(_entrada);
            _entrada = validarMixto(validarEntero.validar(_entrada), _entrada);
            if(_entrada == null){
                return null;
            }
            return _entrada;
        } catch (NumberFormatException ex1) {
            try {
                Double.parseDouble(_entrada);
                _entrada = validarMixto(validarDouble.validar(_entrada), _entrada);
                if(_entrada == null){
                    return null;
                }
                return _entrada;
            } catch (NumberFormatException ex2) {
                _entrada = validarMixto(validarVariableTrueFalse(_entrada), _entrada);
                if(_entrada == null){
                    return null;
                }
                boolean temp = false;
                try{
                    Integer.parseInt(_entrada);
                    temp = true;
                }catch (NumberFormatException ex3){
                    try{
                        Double.parseDouble(_entrada);
                        temp = true;
                    }catch (NumberFormatException ex4){
                    }
                }
                if(!temp){
                    validarVariable(_entrada);
                }
                return _entrada;
            }
        }
    }

    public void ajustarSize(){

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


