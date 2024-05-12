package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DibujoForInicio extends PanelPersonalizado {

    private DibujoForInterno interno;
    private List<PanelPersonalizado> panelesFor;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);

    public DibujoForInicio(String _texto, List<PanelPersonalizado> _lista, JPanel _contenedor, DibujoForInterno _interno){
        super(_texto, _lista, _contenedor);
        this.interno = _interno;
        this.panelesFor = _lista;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if(e.getClickCount() == 2){
                    String textoNuevo = JOptionPane.showInputDialog(null,"Ediiasd:",_texto);
                    if(textoNuevo != null && !textoNuevo.isEmpty()){
                        cambiarTexto(textoNuevo);
                    }
                }

                if(SwingUtilities.isRightMouseButton(e)){
                    JPanel ver = interno.getFalso();
                    List<PanelPersonalizado> l_ver= interno.getListaFalsa();
                    new VentanaEmergenteFor(ver,l_ver,(PanelPersonalizado) _contenedor);
                }

            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int x1 = (int) (anchoPanel*0.171);
        int x2 = (int) (anchoPanel*0.828);
        int y1 = (int) ((altoPanel / 4)+altoPanel*0.15);

        int centro_x = anchoPanel/2;                                         // Centro horizontal
        int centro_y = altoPanel/2;                                        // Centro vertical

        int cuarto = anchoPanel/4;

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,y1);  //Anclaje superior
        g.drawLine(centro_x,y1,centro_x+10,y1-10);
        g.drawLine(centro_x,y1,centro_x-10,y1-10);
        g.drawLine(x1,y1,x2,y1);
        g.drawLine(x2,y1,x2,altoPanel); //Linea vertical derecha
        g.drawLine(x2,altoPanel,x2+10,altoPanel-10); //Flujo derecho
        g.drawLine(x2,altoPanel,x2-10,altoPanel-10);
        g.drawLine(x1,y1,x1,altoPanel); //Linea vertical izquierda
        g.drawLine(x1,y1,x1+10,y1+10); //Flujo izquierdo
        g.drawLine(x1,y1,x1-10,y1+10);

        // fuente con el tamaño especificado
        g.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);

    }

    public List<PanelPersonalizado> getPanelesFor() {
        return panelesFor;
    }

}
