package Dibujos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PanelPersonalizado extends JPanel
{

    protected String texto;

    public PanelPersonalizado(String texto) {

        this.texto = texto;
        setPreferredSize(new Dimension(100, 50));

    }

}