package Dibujos.PanelesMovibles;

import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.For.DibujoForInterno;
import Dibujos.PanelesMovibles.While.DibujoWhileFin;
import Dibujos.PanelesMovibles.While.DibujoWhileInicio;
import Dibujos.PanelesMovibles.While.DibujoWhileInterno;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DibujoWhile extends PanelMovible {

    private JPanel condicion;
    private JPanel contenido;
    private JPanel fin;

    private GridBagConstraints restricciones;

    private List<PanelPersonalizado> lista;


    public DibujoWhile(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                       VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        setPreferredSize(new Dimension(600, 500));

        this.restriciones         = new GridBagConstraints();
        this.restriciones.gridx   = 0;
        this.restriciones.gridy   = GridBagConstraints.RELATIVE; // Se inicia en la siguiente fila
        this.restriciones.anchor  = GridBagConstraints.CENTER; // Alineación vertical superior
        this.restriciones.fill    = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
        this.restriciones.weighty = 0; // No expandir en dirección vertical
        this.restriciones.insets  = new Insets(0, 0, 0, 0);

        this.setLayout(new BoxLayout(DibujoWhile.this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.lista = new ArrayList<>();

        this.contenido = new DibujoWhileInterno();

        this.condicion = new DibujoWhileInicio(this.texto,lista,this,(DibujoWhileInterno) contenido,this.restriciones,null,this.variables,this);
        this.fin = new DibujoWhileFin(texto,lista,this,this.restriciones,null,this.variables);

        this.add(condicion,this.restriciones);
        this.add(contenido,this.restriciones);
        this.add(fin,this.restriciones);
    }

    public JPanel getContenido()
    {
        DibujoWhileInterno aux = (DibujoWhileInterno) this.contenido;
        return aux.getVerdadero2();
    }

    public List<PanelPersonalizado> getLista() {
        DibujoWhileInterno aux = (DibujoWhileInterno) this.contenido;
        return aux.getListaVerdadera2();
    }

    @Override
    public void ajustarSize()
    {
        DibujoWhileInterno aux = (DibujoWhileInterno) this.contenido;

        aux.ajustarSize();

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

    @Override
    public String getTexto() {
        return super.getTexto();
    }



}
