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
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

        int widthTx  = this.anchoAlto[0];
        int heightTx = this.anchoAlto[1];

        int centro_x = getWidth()  / 2;
        int centro_y = getHeight() / 2;

        // Coordenadas del Paralelogramo

        int x1 = centro_x - ( ( widthTx  / 2 ) +10);                    // Coordenada x del lado izquierdo del rectángulo
        int x2 = centro_x + ( ( widthTx  / 2 ) +10);     // Coordenada x del lado derecho del rectángulo
        int y1 = centro_y - ( ( heightTx / 2 ) +10);                 // Coordenada y del lado superior del rectángulo
        int y2 = centro_y + ( ( heightTx / 2 ) +10);
        int desvio = (int) ((x2-x1)*0.1);

        // Dibujar las líneas que forman el paralelogramo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1+desvio, y1, x2+desvio, y1);     // Lado superior
        g2d.drawLine(x2+desvio, y1, x2-desvio, y2);     // Lado derecho
        g2d.drawLine(x2-desvio, y2 , x1-desvio, y2);     // Lado inferior
        g2d.drawLine(x1-desvio, y2 , x1+desvio, y1);     // Lado izquierdo

        // Dibujar flecha de entrada
        g2d.setColor(Color.RED);
        g2d.drawLine(x2 + desvio, y1, x2 + desvio - 10, y1 + 5);              // Linea de
        g2d.drawLine(x2 + desvio, y1, x2 + desvio + 10, y1 - 5);              // Flecha

        g2d.drawLine( x2 + desvio - 10, y1 + 5, x2 + desvio - 7, y1 + 1);  // Punta de
        g2d.drawLine( x2 + desvio - 10, y1 + 5, x2 + desvio - 6, y1 + 5);  // Flecha

        // Dibujar flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x,0,centro_x,y1);               // Linea superior
        g2d.drawLine(centro_x,y2 ,centro_x,getHeight());         // Linea inferior

        g2d.drawLine(centro_x,y1,centro_x+10,y1-10);    //  Flecha
        g2d.drawLine(centro_x,y1,centro_x-10,y1-10);    //  de flujo

        // fuente con el tamaño especificado
        g2d.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(texto, x, y);
    }

    public void cambiarVariable(String varAntigua, String varNueva) {
        System.out.println("-----VarNueva: "+varNueva);
        System.out.println("-----VarAntigua: "+varAntigua);
        for(int i=0; i < variables.size(); i++){
            if(variables.get(i).equals(varAntigua)){
                varNueva = buscarYValidarEntrada(varNueva);
                variables.set(i,varNueva);
                this.texto = varNueva;
                return;
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