package Dibujos.PanelesMovibles;
import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DibujoProceso extends PanelMovible {
    private int ultimoEjeY;
    private boolean moviendo;
    private int ejeYMouse;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);
    public DibujoProceso(String texto, List <PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones,
                         VentanaEmergente _ventanaEmergente,Map<String, Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        splitTexto(texto);
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

    public void splitTexto(String texto){
        String[] tokens = texto.split("\\s+|(?<==)|(?=[+\\-*/])");
        int bandera = 0;
        String key = null;
        String temp = null;
        for (String token : tokens) {
            if(bandera >= 2 && !"+-*/".contains(token)){
                System.out.println("TOKEN = "+token);
                try{
                    Integer.parseInt(token);
                    System.out.println("Conversion entero");
                    token = validarMixto(validarEntero.validar(token),token);
                }catch (NumberFormatException ex1){
                    try{
                        Double.parseDouble(token);
                        System.out.println("Conversion double");
                        token = validarMixto(validarDouble.validar(token),token);
                    }catch (NumberFormatException ex2){
                        System.out.println("Conversion cadena");
                        token = validarMixto(validarVariableTrueFalse(token),token);
                    }
                }
            }
            if(!token.isEmpty()){
                if(bandera >= 2){
                    if(key == null){
                        key = token;
                    }else{
                        key += token;
                    }
                }else{
                    if(bandera == 0){
                        temp = token;
                        temp = validarVariable(temp);
                    }
                }
            }
            bandera++;
        }
        System.out.println();
        variables.put(temp,key);
        this.texto = temp +" = "+key;
        for(Map.Entry<String,Object> entry : variables.entrySet()){
            String var = entry.getKey();
            Object key2 = entry.getValue();
            System.out.println(var+" = "+key2);
        }
    }

}
