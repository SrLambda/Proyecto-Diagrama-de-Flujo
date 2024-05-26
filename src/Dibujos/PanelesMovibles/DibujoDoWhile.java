package Dibujos.PanelesMovibles;

import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DoWhile.DibujoDoWhileFin;
import Dibujos.PanelesMovibles.DoWhile.DibujoDoWhileInicio;
import Dibujos.PanelesMovibles.DoWhile.DibujoDoWhileInterno;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoDoWhile extends PanelMovible {
    private JPanel condicion, contenido, fin;
    private GridBagConstraints restriciones;
    private List<PanelPersonalizado> lista;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);
    public DibujoDoWhile(String texto, List<PanelPersonalizado> lista, JPanel _contenedor,GridBagConstraints _restriciones) {
        super(texto, lista, _contenedor,_restriciones);
        setPreferredSize(new Dimension(200, 500));

        this.setLayout(new BoxLayout(DibujoDoWhile.this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.lista = new ArrayList<>();

        this.condicion = new DibujoDoWhileInicio(this.texto,lista,this,(DibujoDoWhileInterno) contenido, this.restriciones);
        this.contenido = new DibujoDoWhileInterno();
        this.fin = new DibujoDoWhileFin(this.texto,lista,this,(DibujoDoWhileInterno) contenido, this.restriciones);

        this.add(condicion,this.restriciones);
        this.add(contenido,this.restriciones);
        this.add(fin,this.restriciones);
    }
}
