package Dibujos.PanelesMovibles;

import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionFin;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionInicio;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionInterno;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoDecision extends PanelMovible {

    private JPanel condicion, contenido, fin;
    private List<PanelPersonalizado> lista;

    public DibujoDecision(String texto, List<PanelPersonalizado> lista, JPanel _contenedor) {
        super(texto, lista, _contenedor);

        this.setLayout(new BoxLayout(DibujoDecision.this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.lista = new ArrayList<>();

        this.contenido = new DibujoDecisionInterno();

        this.condicion = new DibujoDecisionInicio(this.texto,lista,this);
        this.fin = new DibujoDecisionFin(this.texto,lista,this);

        this.add(condicion);
        this.add(contenido);
        this.add(fin);

    }

    public List<PanelPersonalizado> getVerdad(){

        DibujoDecisionInterno aux = (DibujoDecisionInterno) this.contenido;
        return aux.getListaVerdadera();
    }

    public JPanel getContVerdad(){
        DibujoDecisionInterno aux = (DibujoDecisionInterno) this.contenido;
        return aux.getVerdadero();
    }

    public List<PanelPersonalizado> getFalso(){
        DibujoDecisionInterno aux = (DibujoDecisionInterno) this.contenido;
        return aux.getListaFalsa();
    }

    public JPanel getContFalso(){
        DibujoDecisionInterno aux = (DibujoDecisionInterno) this.contenido;
        return aux.getFalso();
    }
}
