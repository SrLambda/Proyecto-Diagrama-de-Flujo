package Dibujos.PanelesMovibles;
import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class DibujoSalida extends PanelMovible {
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);
    private String salidaS;
    public DibujoSalida(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                        VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        String nuevoTxt = quitarEspacios(texto);
        verVariable(nuevoTxt);
        manejoSalidas();
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        // Ajustar para zoom centralizado
        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.scale(zoomFactor, zoomFactor);
        g2d.translate(-getWidth() / 2, -getHeight() / 2);

        int widthTx  = this.anchoAlto[0];
        int heightTx = this.anchoAlto[1];

        int centro_x = getWidth()  / 2;
        int centro_y = getHeight() / 2;

        // Coordenadas del Paralelogramo

        int x1 = centro_x - ( ( widthTx  / 2 ) +10);                    // Coordenada x del lado izquierdo del rectángulo
        int x2 = centro_x + ( ( widthTx  / 2 ) +10);     // Coordenada x del lado derecho del rectángulo
        int y1 = centro_y - ( ( heightTx / 2 ) +10);                 // Coordenada y del lado superior del rectángulo
        int y2 = centro_y + ( ( heightTx / 2 ) +10);
        int desvio = (int) ((x2-x1)*0.1);                                    // Inclinación horizontal del paralelogramo


        // Dibujar las líneas que forman el paralelogramo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1+desvio, y1, x2+desvio, y1);     // Lado superior
        g2d.drawLine(x2+desvio, y1, x2-desvio, y2 );     // Lado derecho
        g2d.drawLine(x1-desvio, y2 , x1+desvio, y1);     // Lado izquierdo
        g2d.drawLine(x2-desvio, y2 , x1-desvio, y2 );     // Lado inferior

        // Dibujar flecha de salida
        g2d.setColor(Color.RED);
        g2d.drawLine(x2+desvio, y1, x2+desvio - 10, y1 + 5);
        g2d.drawLine(x2+desvio, y1, x2+desvio + 10, y1 - 5);
        g2d.drawLine(x2+desvio + 10, y1 - 5,x2+desvio + 7,y1 - 1);
        g2d.drawLine(x2+desvio + 10, y1 - 5,x2+desvio + 6,y1 - 5);

        // Dibujar flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x,0,centro_x,y1);               // Linea superior
        g2d.drawLine(centro_x,y2 ,centro_x,getHeight() + 30);         // Linea inferior

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

    public void verVariable(String _entrada) {
        if(_entrada == null){
            return;
        }
        while(!validarVariableTrueFalse(_entrada)){
            _entrada = JOptionPane.showInputDialog(null, "Variable ["+_entrada+"] no creada");
            if(_entrada == null){
                return;
            }
        }
        this.setSalidaS(_entrada);
    }


    public String getSalidaS() {
        return this.salidaS;
    }

    public void setSalidaS(String _salidaS) {
        this.salidaS = _salidaS;
    }

    public void manejoSalidas(){
        if(getSalidaS() == null){
            this.texto = null;
            return;
        }
        indice1 = indice1 + 1;
        System.out.println(indice1);
        this.setTipo("Salida"+indice1);
        this.texto = getSalidaS();
        this.variables.add("Salida"+indice1);
        this.variables.add(this.getSalidaS());
    }

}
