package Dibujos.PanelesMovibles;
import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoProceso extends PanelMovible {
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;
    private String variableS;
    private String procesoS;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);
    public DibujoProceso(String texto, List <PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones,
                         VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        String nuevoTxt = quitarEspacios(texto);
        asignarVariable(nuevoTxt);
        manejo();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);


        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Coordenadas del rectángulo
        int x1 = (int) ((panelWidth / 4)+panelWidth*0.1);                    // Coordenada x del lado izquierdo del rectángulo
        int x2 = (int) ((panelWidth - (panelWidth / 4))-panelWidth*0.1);     // Coordenada x del lado derecho del rectángulo
        int y1 = (int) ((panelHeight / 4)+panelHeight*0.15);                 // Coordenada y del lado superior del rectángulo
        int y2 = (int) ((panelHeight - (panelHeight / 4))-panelHeight*0.15); // Coordenada y del lado inferior del rectángulo

        // Coordenadas de Flujo
        int centro_x = panelWidth/2;


        // Dibujar las líneas que forman el rectángulo
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y1);     // Lado superior
        g.drawLine(x2, y1, x2, y2);     // Lado derecho
        g.drawLine(x2, y2, x1, y2);     // Lado inferior
        g.drawLine(x1, y2, x1, y1);     // Lado izquierdo

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,y1);            // Linea superior
        g.drawLine(centro_x,y2,centro_x,panelHeight);     // Linea inferior
        g.drawLine(centro_x,y1,centro_x+10,y1-10);
        g.drawLine(centro_x,y1,centro_x-10,y1-10);


        // fuente con el tamaño especificado
        g.setFont(textoFont);

        // Dibuja el texto en el centro del panel
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);

    }

    public void asignarVariable(String _entrada){
        int posicion = _entrada.indexOf('=');
        while(posicion == -1 || _entrada.length() <3){
            _entrada = JOptionPane.showInputDialog(null, "Proceso incorrecto", _entrada);
            if(_entrada == null){
                return;
            }
            _entrada = quitarEspacios(_entrada);
            posicion = _entrada.indexOf('=');
        }
        this.setVariableS(_entrada.substring(0, posicion));
        this.texto = _entrada;
    }

    public void asignarProceso(String _entrada){
        int posicion = _entrada.indexOf('=');
        while(posicion == -1 || _entrada.length() <3){
            _entrada = JOptionPane.showInputDialog(null, "Proceso incorrecto", _entrada);
            if(_entrada == null){
                return;
            }
            _entrada = quitarEspacios(_entrada);
            posicion = _entrada.indexOf('=');
        }
        this.setProcesoS(_entrada.substring(posicion + 1));
    }

    public boolean verVariable(String _entrada) {
        while(!validarVariableTrueFalse(_entrada)){
            _entrada = JOptionPane.showInputDialog(null, "Variable ["+_entrada+"] no creada");
            if(_entrada == null){
                return false;
            }
        }
        this.setVariableS(_entrada);
        return true;
    }

    public boolean verProceso(String _entrada){
        List<String> partes = new ArrayList<>();
        StringBuilder parte = new StringBuilder();
        for (int i = 0; i < _entrada.length(); i++) {
            char c = _entrada.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (parte.length() > 0) {
                    partes.add(buscar(parte.toString()));
                    parte.setLength(0);
                }
                partes.add(String.valueOf(c));
            } else {
                parte.append(c);
            }
        }
        if (parte.length() > 0) {
            partes.add(buscar(parte.toString()));
        }
        this.setProcesoS(String.join("",partes));
        this.texto = this.getVariableS() +" = "+ this.getProcesoS();
        return true;
    }

    public void asignarALista(String _variable, String _asignacion){
        for(int i=0; i < variables.size()-1; i++){
            if(variables.get(i).equals(_variable)){
                variables.set(i+1,_asignacion);
            }
        }
    }

    public String getVariableS() {
        return this.variableS;
    }

    public void setVariableS(String _variableS) {
        this.variableS = _variableS;
    }

    public String getProcesoS() {
        return this.procesoS;
    }

    public void setProcesoS(String _procesoS) {
        this.procesoS = _procesoS;
    }

    public void manejo(){

        if(this.getVariableS() == null){
            this.texto = getVariableS();
            return;
        }
        asignarProceso(this.texto);

        if(this.getProcesoS() == null){
            this.texto = getVariableS();
            return;
        }

        if(!verVariable(this.getVariableS())){
            this.texto = null;
            return;
        }

        if(!verProceso(this.getProcesoS())){
            this.texto = null;
            return;
        }

        if(this.getProcesoS().equals("null")){
            this.texto = null;
            return;
        }

        asignarALista(this.getVariableS(), this.getProcesoS());
    }

}
