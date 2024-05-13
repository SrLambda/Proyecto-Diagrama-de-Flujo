package Dibujos.PanelesMovibles;

import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.While.DibujoWhileFin;
import Dibujos.PanelesMovibles.While.DibujoWhileInicio;
import Dibujos.PanelesMovibles.While.DibujoWhileInterno;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoDoWhile extends PanelMovible {
    private JPanel condicion, contenido, fin;
    private List<PanelPersonalizado> lista;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);
    public DibujoDoWhile(String texto, List<PanelPersonalizado> lista, JPanel _contenedor) {
        super(texto, lista, _contenedor);
        setPreferredSize(new Dimension(200, 500));

        this.setLayout(new BoxLayout(DibujoDoWhile.this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.lista = new ArrayList<>();

        this.contenido = new DibujoWhileInterno();

        this.condicion = new DibujoWhileInicio(this.texto,lista,this,(DibujoWhileInterno) contenido);
        this.fin = new DibujoWhileFin(texto,lista,this);

        this.add(condicion);
        this.add(contenido);
        this.add(fin);
    }
}
