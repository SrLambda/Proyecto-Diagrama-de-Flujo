package Dibujos.PanelesMovibles;
import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoProceso extends PanelMovible {
    private String variableS;
    private String procesoS;

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
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

        int widthTx  = this.anchoAlto[0];
        int heightTx = this.anchoAlto[1];

        int centro_x = this.getWidth()  / 2;
        int centro_y = this.getHeight() / 2;

        // Coordenadas del rectángulo
        int x1 = centro_x - ( ( widthTx  / 2 ) + 10); // Coordenada x del lado izquierdo del rectángulo
        int x2 = centro_x + ( ( widthTx  / 2 ) + 10); // Coordenada x del lado derecho del rectángulo
        int y1 = centro_y - ( ( heightTx / 2 ) + 10); // Coordenada y del lado superior del rectángulo
        int y2 = centro_y + ( ( heightTx / 2 ) + 10); // Coordenada y del lado inferior del rectángulo


        // Dibujar las líneas que forman el rectángulo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1, y1, x2, y1);     // Lado superior
        g2d.drawLine(x2, y1, x2, y2);     // Lado derecho
        g2d.drawLine(x2, y2, x1, y2);     // Lado inferior
        g2d.drawLine(x1, y2, x1, y1);     // Lado izquierdo

        // Dibujar flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x,0,centro_x,y1);            // Linea superior
        g2d.drawLine(centro_x,y2,centro_x,getHeight());     // Linea inferior
        g2d.drawLine(centro_x,y1,centro_x+10,y1-10);
        g2d.drawLine(centro_x,y1,centro_x-10,y1-10);


        // fuente con el tamaño especificado
        g2d.setFont(textoFont);

        // Dibuja el texto en el centro del panel
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(texto, x, y);

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
        this.texto = this.getVariableS() +"="+ this.getProcesoS();
        return true;
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
            this.texto = null;
            return;
        }
        asignarProceso(this.texto);

        if(this.getProcesoS() == null){
            this.texto = null;
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
        indice1 = indice1 + 1;
        this.setTipo("Proceso"+indice1);
        variables.add("Proceso"+indice1);
        variables.add(getVariableS()+"="+getProcesoS());

    }

}
