package Dibujos.PanelesMovibles;

import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Mapa.Mapa;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InstruccionFor extends PanelMovible {

    public InstruccionFor(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, Mapa _mapa){
        super(texto,lista,_contenedor,_mapa);

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

        System.out.println("x1: "+x1);
        System.out.println("x2: "+x2);
        System.out.println("y1: "+y1);
        System.out.println("y2: "+y2);

        // Dibujar las líneas que forman el rectángulo
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y1);     // Lado superior
        g.drawLine(x2, y1, x2, y2 + 30);     // Lado derecho
        g.drawLine(x2, y2 + 30, x1, y2 + 30);     // Lado inferior
        g.drawLine(x1, y2 + 30, x1, y1);     // Lado izquierdo

    }

    @Override
    protected void cambiarValor() {
    }

    @Override
    protected void agregarValorAlMapa() {
    }
}
