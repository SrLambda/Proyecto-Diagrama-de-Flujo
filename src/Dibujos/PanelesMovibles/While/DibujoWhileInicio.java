package Dibujos.PanelesMovibles.While;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DibujoWhileInicio extends PanelPersonalizado {
    private DibujoWhileInterno interno;
    private List<PanelPersonalizado> panelesCiclo;

    public DibujoWhileInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoWhileInterno _interno,GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente);
        this.interno = _interno;
        panelesCiclo = lista;
        setPreferredSize(new Dimension(600, 500));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic para editar el texto
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
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

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
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1, centro_y,centro_x, y1);     // Lado superior
        g2d.drawLine(x2, centro_y,centro_x, y1);     // Lado derecho
        g2d.drawLine(x1, centro_y,centro_x, y2);     // Lado inferior
        g2d.drawLine(x2, centro_y,centro_x, y2);     // Lado izquierdo

        // Dibujar flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x,0,centro_x,y1);  // Linea superior
        g2d.drawLine(centro_x,y2,centro_x,panelHeight);     // Linea inferior
        g2d.drawLine(x2,centro_y, panelWidth - 100,centro_y); //  Linea horizontal derecha
        g2d.drawLine(100,(int) (panelHeight*0.25),centro_x,(int) (panelHeight*0.25)); //  Linea horizontal izquierda

        g2d.drawLine(panelWidth - 100,centro_y,panelWidth - 100,panelHeight); //  Linea vertical derecha
        g2d.drawLine(100,(int) (panelHeight*0.25),100,panelHeight); //  Linea vertical izquierda

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
    public List<PanelPersonalizado> getPanelesCiclo() {
        return panelesCiclo;
    }
}
