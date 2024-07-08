package Dibujos.PanelesMovibles;
import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.List;
import java.util.Map;

public class DibujoDocumento extends PanelMovible {
    private String salidaS;
    public DibujoDocumento(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                           VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        manejoSalidas(texto);
    }

    public String getSalidaS() {
        return this.salidaS;
    }

    public void setSalidaS(String _salidaS) {
        this.salidaS = _salidaS;
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

        int centro_x = this.getWidth()  / 2;
        int centro_y = this.getHeight() / 2;

        // Coordenadas del rectángulo
        int x1 = centro_x - ( ( widthTx  / 2 ) + 10); // Coordenada x del lado izquierdo del rectángulo
        int x2 = centro_x + ( ( widthTx  / 2 ) + 10); // Coordenada x del lado derecho del rectángulo
        int y1 = centro_y - ( ( heightTx / 2 ) + 10); // Coordenada y del lado superior del rectángulo
        int y2 = centro_y + ( ( heightTx / 2 ) + 10); // Coordenada y del lado inferior del rectángulo

        double ctrl1x = (x1 + (x1 / 2) * 0.3);
        double ctrl1y = -1.1 * (y2);
        double ctrl2x = (x2 + (x2 / 4) * -0.3);
        double ctrl2y = 0.9 * (y2);

        // Dibujar las líneas que forman el rectángulo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1, y1, x2, y1);     // Lado superior
        g2d.drawLine(x2, y1, x2, y2);     // Lado derecho
        g2d.drawLine(x1, y2, x1, y1);     // Lado izquierdo
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1)); // Grosor de línea
        g2d.draw(new QuadCurve2D.Double(x1, y2, ctrl1x, -ctrl1y, centro_x, y2));
        g2d.draw(new QuadCurve2D.Double(centro_x, y2, ctrl2x, ctrl2y, x2, y2));

        // Dibujar flujo
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x,0,centro_x,y1);        // Linea superior
        g2d.drawLine(centro_x,y2,centro_x,this.getHeight());  // Linea inferior
        g2d.drawLine(centro_x,y1,centro_x+10,y1-10);
        g2d.drawLine(centro_x,y1,centro_x-10,y1-10);

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


    public void manejoSalidas(String _texto){
        String nuevoTxt = quitarEspacios(_texto);
        verVariable(nuevoTxt);
        if(getSalidaS() == null){
            this.texto = null;
            return;
        }
        this.texto = getSalidaS();
        indice1 = indice1 + 1;
        this.setTipo("Documento"+indice1);
        this.setEntrada(this.getSalidaS());
        this.variables.add("Documento"+indice1);
        this.variables.add(this.getSalidaS());
    }

}





