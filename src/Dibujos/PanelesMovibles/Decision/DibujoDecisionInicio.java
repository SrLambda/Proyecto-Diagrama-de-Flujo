package Dibujos.PanelesMovibles.Decision;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DibujoDecisionInicio extends PanelPersonalizado {

    public DibujoDecisionInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                                VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        String nuevoTxt = quitarEspacios(texto);

        addMouseListener(new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) { // Doble clic para editar el texto
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto); // Actualizar el texto de la figura
                    }
                }

            }
        });
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int widthTx  = this.anchoAlto[0];
        int heightTx = this.anchoAlto[1];

        int panelWidth  = getWidth();
        int panelHeight = getHeight();


        int centro_x = panelWidth/2;  // Centro horizontal
        int centro_y = panelHeight/2; // Centro vertical


        int cuarto_x = panelWidth/4;

        double anguloY = Math.PI / 6;
        double anguloX = Math.PI - anguloY;

        double z1 = Math.sqrt( (widthTx*widthTx)   / (2 - (2 * Math.cos( anguloX ) ) ) );
        double z2 = Math.sqrt( (heightTx*heightTx) / (2 - (2 * Math.cos( anguloY ) ) ) );

        double z = z1+z2;

        int deltX = (int) (z * Math.cos( anguloY / 2));
        int deltY = (int) (z * Math.sin( anguloY / 2));


        // Coordenadas del rombo
        int x1 = centro_x - deltX; // Coordenada x del lado izquierdo del rectángulo
        int x2 = centro_x + deltX; // Coordenada x del lado derecho del rectángulo
        int y1 = centro_y - deltY; // Coordenada y del lado superior del rectángulo
        int y2 = centro_y + deltY; // Coordenada y del lado inferior del rectángulo



        // Dibujar las líneas que forman el rombo
        g.setColor(Color.BLACK);
        g.drawLine(x1, centro_y,centro_x, y1);     // Lado superior
        g.drawLine(x2, centro_y,centro_x, y1);     // Lado derecho
        g.drawLine(x1, centro_y,centro_x, y2);     // Lado inferior
        g.drawLine(x2, centro_y,centro_x, y2);     // Lado izquierdo

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,y1);  // Linea superior

        g.drawLine(centro_x,y1,centro_x,y1);   // Flecha
        g.drawLine(centro_x,y1,centro_x,y1);   // de flujo

        g.drawLine(x1,centro_y,cuarto_x,centro_y);
        g.drawLine(x2,centro_y,cuarto_x*3,centro_y);
        g.drawLine(cuarto_x,centro_y,cuarto_x,panelHeight);
        g.drawLine(cuarto_x*3,centro_y,cuarto_x*3,panelHeight);

        // fuente con el tamaño especificado
        g.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }

    public void verCondicion(String _entrada) {
        List<String> partes = new ArrayList<>();
        StringBuilder parte = new StringBuilder();
        char[] operadores = {'+', '-', '*', '/', '<', '>', '=', '!'};

        for (int i = 0; i < _entrada.length(); i++) {
            char c = _entrada.charAt(i);
        }
    }

    public boolean condicion(char c, char[] operadores) {
        for (int i = 0; i < operadores.length; i++) {
            if (c == operadores[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean condicionCompuesta(String operador) {
        String[] operadoresCompuestos = {">=", "<=", "==", "!="};
        for (int i = 0; i < operadoresCompuestos.length; i++) {
            if (operador.equals(operadoresCompuestos[i])) {
                return true;
            }
        }
        return false;
    }

}
