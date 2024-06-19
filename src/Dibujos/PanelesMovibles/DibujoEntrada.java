package Dibujos.PanelesMovibles;

import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class DibujoEntrada extends PanelMovible {
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);
    public DibujoEntrada(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                         VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        this.texto = buscarYValidarEntrada(texto);
        this.variables.add(this.indice,this.texto);
        System.out.println("Indice actual: "+indice);
        System.out.println("Variable "+"'"+this.texto+"'"+" agregada");
        this.indice += 2;
        System.out.println("Indice siguiente variable: "+this.indice);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Coordenadas del Paralelogramo
        int x1 = (int) ((panelWidth / 4) + panelWidth * 0.1);                    // Coordenada x del lado izquierdo del paralelogramo
        int x2 = (int) ((panelWidth - (panelWidth / 4)) - panelWidth * 0.1);     // Coordenada x del lado derecho del paralelogramo
        int y1 = (int) ((panelHeight / 4) + panelHeight * 0.15);                 // Coordenada y del lado superior del paralelogramo
        int y2 = (int) ((panelHeight - (panelHeight / 4)) - panelHeight * 0.15); // Coordenada y del lado inferior del paralelogramo
        int desvio = (int) ((x2 - x1) * 0.1);                                    // Inclinación horizontal del paralelogramo

        int centro_x = panelWidth / 2;

        // Dibujar las líneas que forman el paralelogramo
        g.setColor(Color.BLACK);
        g.drawLine(x1+desvio, y1, x2+desvio, y1);     // Lado superior
        g.drawLine(x2+desvio, y1, x2-desvio, y2 + 30);     // Lado derecho
        g.drawLine(x2-desvio, y2 + 30, x1-desvio, y2 + 30);     // Lado inferior
        g.drawLine(x1-desvio, y2 + 30, x1+desvio, y1);     // Lado izquierdo


        // Dibujar flecha de entrada
        g.setColor(Color.RED);
        g.drawLine(x2 + desvio, y1, x2 + desvio - 10, y1 + 5);              // Linea de
        g.drawLine(x2 + desvio, y1, x2 + desvio + 10, y1 - 5);              // Flecha

        g.drawLine( x2 + desvio - 10, y1 + 5, x2 + desvio - 7, y1 + 1);  // Punta de
        g.drawLine( x2 + desvio - 10, y1 + 5, x2 + desvio - 6, y1 + 5);  // Flecha

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,y1);               // Linea superior
        g.drawLine(centro_x,y2 + 30,centro_x,panelHeight + 30);         // Linea inferior

        g.drawLine(centro_x,y1,centro_x+10,y1-10);    //  Flecha
        g.drawLine(centro_x,y1,centro_x-10,y1-10);    //  de flujo

        // fuente con el tamaño especificado
        g.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }

    public void cambiarVariable(List <Object> _variables, String varAntigua, String varNueva) {
        System.out.println("-----VarNueva: "+varNueva);
        System.out.println("-----VarAntigua: "+varAntigua);
        varNueva = buscarYValidarEntrada(varAntigua);
        for(int i=0; i<_variables.size(); i++){
            if(_variables.get(i).equals(varNueva)){
                _variables.set(i,varNueva);
            }
        }
    }

    public String buscarYValidarEntrada(String _entrada){
        boolean encontrado;
        boolean entradaValida = false;
        while(!entradaValida){
            encontrado = false;
            for(int i=0; i<variables.size(); i++){
                if(variables.get(i).equals(_entrada)){
                    _entrada = JOptionPane.showInputDialog(null, "La variable ya existe", _entrada);
                    encontrado = true;
                    break;
                }
            }
            if(!encontrado){
                String nuevaEntrada = validar(validarCadena.validar(_entrada),"Cadena",_entrada);
                if(nuevaEntrada != null && !nuevaEntrada.isEmpty()){
                    boolean duplicados = false;
                    for(Object var : variables){
                        if(var.equals(nuevaEntrada)){
                            duplicados = true;
                            break;
                        }
                    }
                    if(!duplicados){
                        _entrada = nuevaEntrada;
                        entradaValida = true;
                    }else{
                        _entrada = nuevaEntrada;
                        _entrada = JOptionPane.showInputDialog(null, "La variable ya existe", _entrada);
                    }
                }else{
                    _entrada = JOptionPane.showInputDialog(null, "La variable ya existe", _entrada);
                }
            }
        }
        return _entrada;
    }

}