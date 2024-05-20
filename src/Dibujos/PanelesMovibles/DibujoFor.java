package Dibujos.PanelesMovibles;

import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.For.DibujoForFin;
import Dibujos.PanelesMovibles.For.DibujoForInicio;
import Dibujos.PanelesMovibles.For.DibujoForInterno;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoFor extends PanelMovible {

    private JPanel condicion, contenido, fin;
    private List<PanelPersonalizado> lista;
    private GridBagConstraints    restriciones;

    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);

    public DibujoFor(String texto, List<PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones){
        super(texto,lista,_contenedor,_restriciones);
        setPreferredSize(new Dimension(200, 500));

        this.setLayout(new BoxLayout(DibujoFor.this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.lista = new ArrayList<>();

        this.contenido = new DibujoForInterno(texto,0,0,0);

        this.condicion = new DibujoForInicio(this.texto,lista,this,(DibujoForInterno) contenido,this.restriciones);
        this.fin = new DibujoForFin(texto,lista,this,this.restriciones);

        this.add(condicion);
        this.add(contenido);
        this.add(fin);

    }
}
