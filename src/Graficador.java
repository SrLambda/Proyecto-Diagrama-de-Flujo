import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

public class Graficador extends JFrame {
    private JPanel panel1;
    private JPanel panel;
    private JButton inicioFinButton;
    private JButton lineaDeFlujoButton;
    private JButton etapaDelProcesoButton;
    private JButton decisionButton;
    private JButton entradaSalidaButton;
    private JButton documentoButton;
    private JButton limpiarButton;

    public Graficador(){

        setContentPane(panel);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Graficador Interactivo de Diagramas de flujo");
        setVisible(true);

        //Point2D punto1 = new Point2D.Double(10, 10);
        //Line2D linea1 = new Line2D.Double(50, 50, 150, 150);
        //QuadCurve2D curvaCuadratica1 = new QuadCurve2D.Double(50, 50, 100, 150, 150, 50);

        //botón para dibujar un rectángulo
        etapaDelProcesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = panel1.getGraphics();
                g.setColor(Color.BLUE);
                g.drawLine(100, 100, 200, 100);
                g.drawLine(100, 100, 100, 40);
                g.drawLine(200, 100, 200, 40);
                g.drawLine(100, 40, 200, 40);
            }
        });

        //boton para dibujar un rombo
        decisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = panel1.getGraphics();
                g.setColor(Color.RED);
                g.drawLine(100,70,150, 100);
                g.drawLine(150,100, 100, 130);
                g.drawLine(100, 130, 50,100);
                g.drawLine(50, 100, 100, 70);
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.removeAll();
                panel1.repaint();
            }
        });
        entradaSalidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
