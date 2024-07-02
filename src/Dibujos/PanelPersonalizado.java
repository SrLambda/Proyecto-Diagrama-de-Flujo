package Dibujos;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

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
    protected Validador validarEntero;
    protected Validador validarDouble;
    protected Validador validarCadena;
    protected GridBagConstraints restriciones;
    protected List <Object> variables;
    protected static int indice1 = 0;
    private String tipo;


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

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public void cambiarTexto(String nuevoTexto) {

        this.texto = nuevoTexto;
        repaint();// Redibujar la figura con el nuevo texto
        revalidate();

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
            ventanaEmergente.actualizarCompnentes();
        }
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String _texto){
        this.texto = _texto;
    }

    public void actualizarContenedor(List<PanelPersonalizado> list, JPanel cont) {
        this.listaFiguras = list;
        this.contenedor = cont;
    }

    public String validar(boolean evidencia, String opcion, String entrada) {
        while (true) {
            if (evidencia) {
                return entrada;
            } else {
                this.texto = entrada;
                entrada = JOptionPane.showInputDialog(null, "Variable invalida", this.texto);
                if(entrada == null){
                    this.texto = null;
                    return null;
                }
                this.texto = entrada;
                if (opcion.equals("Cadena")) {
                    evidencia = validarCadena.validar(entrada);
                } else if (opcion.equals("Entero")) {
                    evidencia = validarEntero.validar(entrada);
                } else if (opcion.equals("Double")) {
                    evidencia = validarDouble.validar(entrada);
                }
                if (evidencia) {
                    return entrada;
                }
            }
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


