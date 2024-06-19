package Dibujos.PanelesMovibles.DoWhile;

import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DibujoDoWhileFin extends PanelPersonalizado {
    private DibujoDoWhileInterno interno;
    private List<PanelPersonalizado> panelesCiclo;

    public DibujoDoWhileFin(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoDoWhileInterno _interno,GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(texto, lista, _contenedor, _restriciones,_ventanaEmergente);
        this.interno = _interno;
        panelesCiclo = lista;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto);
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
            g.drawLine(centro_x,0,centro_x,y1);  // Linea superior
            g.drawLine(centro_x,y2,centro_x,panelHeight);     // Linea inferior
            g.drawLine(x1, centro_y,100, centro_y); // Linea horizontal izquierda
            g.drawLine(100,0,100, centro_y);     // Linea vertical izquierda
            g.drawLine(centro_x,y1,centro_x+10,y1-10);    //  Flecha
            g.drawLine(centro_x,y1,centro_x-10,y1-10);    //  de flujo

            // Dibujar el texto centrado
            g.setFont(textoFont);
            FontMetrics metrics = g.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(texto)) / 2;
            int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
            g.drawString(texto, x, y);
        }

        public List<PanelPersonalizado> getListaFiguras() {
            return listaFiguras;
        }
}

