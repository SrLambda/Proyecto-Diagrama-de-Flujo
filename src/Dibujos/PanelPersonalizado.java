package Dibujos;

import javax.swing.*;
import java.awt.*;

public class PanelPersonalizado extends JPanel
{

    protected String texto;


    public PanelPersonalizado(String texto) {

        this.texto = texto;
        setPreferredSize(new Dimension(100, 50));

    }

}