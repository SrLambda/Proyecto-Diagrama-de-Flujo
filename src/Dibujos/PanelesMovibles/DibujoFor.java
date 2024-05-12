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
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);

    public DibujoFor(String texto, List<PanelPersonalizado> lista, JPanel _contenedor){
        super(texto,lista,_contenedor);
        setPreferredSize(new Dimension(200, 500));

        this.setLayout(new BoxLayout(DibujoFor.this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.lista = new ArrayList<>();

        this.contenido = new DibujoForInterno();

        this.condicion = new DibujoForInicio(this.texto,lista,this,(DibujoForInterno) contenido);
        this.fin = new DibujoForFin(texto,lista,this);

        this.add(condicion);
        this.add(contenido);
        this.add(fin);

    }
}
