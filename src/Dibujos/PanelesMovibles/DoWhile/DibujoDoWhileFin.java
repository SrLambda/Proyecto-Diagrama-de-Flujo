package Dibujos.PanelesMovibles.DoWhile;

import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.While.DibujoWhileInterno;
import Dibujos.PanelesMovibles.While.VentanaEmergenteWhile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DibujoDoWhileFin extends PanelPersonalizado {
    private DibujoDoWhileInterno interno;
    private List<PanelPersonalizado> panelesCiclo;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);

    public DibujoDoWhileFin(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoDoWhileInterno _interno) {
        super(texto, lista, _contenedor);
        this.interno = _interno;
        panelesCiclo = lista;
        setPreferredSize(new Dimension(200, 400));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic para editar el texto
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto); // Actualizar el texto de la figura
                    }
                }

                if (SwingUtilities.isRightMouseButton(e)) {

                    //Verdad
                    JPanel ver = interno.getVerdadero2();
                    List<PanelPersonalizado> l_ver= interno.getListaVerdadera2();

                    new VentanaEmergenteDoWhile(ver,l_ver,(PanelPersonalizado) _contenedor);

                    //Falso
                    /*JPanel fal= interno.getFalso();
                    List<PanelPersonalizado> l_fal= interno.getListaFalsa();
                    new VentanaEmergenteDoWhile(ver,fal,l_ver,l_fal,(PanelPersonalizado) _contenedor);*/
                }
            }
        });
        /*this.falso = falso;
        setPreferredSize(new Dimension(200, 400));
        //this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // FlowLayout con alineación centrada y espacios 01
        this.setLayout(new BoxLayout(DibujoDoWhileFin.this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.falso = new DibujoDoWhileFin.DoWhileFin();

        this.falso.setLayout(new BoxLayout(this.falso, BoxLayout.Y_AXIS));
        this.falso.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.falso.add(new DoWhileVacio(null,null,null));

        this.add(falso);*/
    }

    /*public java.util.List<PanelPersonalizado> getListaFalsa()
    {
        return falso.getListaFiguras();
    }

    public JPanel getFalso() {
        return falso;
    }*/

    /*public class DoWhileFin extends JPanel {
        private final List<PanelPersonalizado> listaFiguras;

        DoWhileFin() {
            listaFiguras = new ArrayList<>();
        }*/

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int centro_x = panelWidth / 2;                                         // Centro horizontal
            int centro_y = panelHeight / 2;                                        // Centro vertical

            int cuarto = panelWidth / 4;

            // Coordenadas del rombo
            int x1 = (int) ((panelWidth / 4) + panelWidth * 0.1);                    // Coordenada x del lado izquierdo del rectángulo
            int x2 = (int) ((panelWidth - (panelWidth / 4)) - panelWidth * 0.1);     // Coordenada x del lado derecho del rectángulo
            int y1 = (int) ((panelHeight / 4) + panelHeight * 0.15);                 // Coordenada y del lado superior del rectángulo
            int y2 = (int) ((panelHeight - (panelHeight / 4)) - panelHeight * 0.15); // Coordenada y del lado inferior del rectángulo

            // Dibujar las líneas que forman el rombo
            g.setColor(Color.BLACK);
            g.drawLine(x1, centro_y, centro_x, y1);     // Lado superior
            g.drawLine(x2, centro_y, centro_x, y1);     // Lado derecho
            g.drawLine(x1, centro_y, centro_x, y2);     // Lado inferior
            g.drawLine(x2, centro_y, centro_x, y2);     // Lado izquierdo

            // Dibujar flujo
            g.drawLine(centro_x,0,centro_x,y1);  // Linea superior
            g.drawLine(centro_x,y2,centro_x,panelHeight);     // Linea inferior
            g.drawLine((int) (centro_x/1.4), centro_y,centro_x/3, centro_y); // Linea horizontal izquierda
            g.drawLine(centro_x/3,0,centro_x/3, centro_y);     // Linea vertical izquierda
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

