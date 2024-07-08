package Dibujos.PanelesMovibles;
import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.Decision.DesicionVacia;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionFin;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionInicio;
import Dibujos.PanelesMovibles.Decision.DibujoDecisionInterno;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DibujoDecision extends PanelMovible {
    private DibujoDecisionInicio  condicion;
    private DibujoDecisionInterno contenido;
    private DibujoDecisionFin     fin;
    private GridBagConstraints    restriciones;


    public DibujoDecision(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                          VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);

        this.restriciones         = new GridBagConstraints();
        this.restriciones.gridx   = 0;
        this.restriciones.gridy   = GridBagConstraints.RELATIVE; // Se inicia en la siguiente fila
        this.restriciones.anchor  = GridBagConstraints.CENTER; // Alineación vertical superior
        this.restriciones.fill    = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
        this.restriciones.weighty = 0; // No expandir en dirección vertical
        this.restriciones.insets  = new Insets(0, 0, 0, 0); // Sin espacio entre paneles

        setPreferredSize(new Dimension(400, 300));
        this.setLayout(new BoxLayout(DibujoDecision.this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.contenido = new DibujoDecisionInterno();
        this.fin = new DibujoDecisionFin(this.texto,lista,this,this.restriciones,this.ventanaEmergente,this.variables);
        this.condicion = new DibujoDecisionInicio(this.texto,lista,this,this.restriciones,this.ventanaEmergente,this.variables,
                this);

        this.add(condicion,this.restriciones);
        this.add(contenido,this.restriciones);
        this.add(fin,this.restriciones);
    }


    public List<PanelPersonalizado> getVerdad(){

        return this.contenido.getListaVerdadera();
    }

    public JPanel getContVerdad(){

        return this.contenido.getVerdadero();
    }

    public List<PanelPersonalizado> getFalso(){

        return this.contenido.getListaFalsa();
    }

    public JPanel getContFalso(){

        return this.contenido.getFalso();
    }

    @Override
    public void ajustarSize()
    {

        this.contenido.ajustarSize();


        int altura = 0;
        int ancho  = 0;

        altura += (int) this.condicion.getPreferredSize().getHeight();
        altura += (int) this.contenido.getPreferredSize().getHeight();
        altura += (int) this.fin.getPreferredSize().getHeight();

        ancho  += (int) this.contenido.getPreferredSize().getWidth();


        Dimension size = new Dimension(ancho,altura);
        this.setPreferredSize(size);
        this.revalidate();

    }

    public void eliminarEspaciosEnBlanco() {

        if( !this.getVerdad().isEmpty() )
        {

            for (int i = 0; i < this.getContVerdad().getComponents().length; i++)
            {

                if (this.getContVerdad().getComponents()[i] instanceof DesicionVacia)
                {
                    this.getContVerdad().remove(i);
                }

            }

        }

        if( !this.getFalso().isEmpty() )
        {

            for (int i = 0; i < this.getContFalso().getComponents().length; i++)
            {

                if (this.getContFalso().getComponents()[i] instanceof DesicionVacia)
                {
                    this.getContFalso().remove(i);
                }

            }

        }
    }

}
