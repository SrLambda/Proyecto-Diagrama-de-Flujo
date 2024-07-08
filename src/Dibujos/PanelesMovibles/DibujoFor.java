package Dibujos.PanelesMovibles;
import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.For.DibujoForFin;
import Dibujos.PanelesMovibles.For.DibujoForInicio;
import Dibujos.PanelesMovibles.For.DibujoForInterno;
import Dibujos.Ventana.VentanaEmergente;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoFor extends PanelMovible {

    private JPanel condicion;
    private JPanel contenido;
    private JPanel fin;
    private List<PanelPersonalizado> lista;
    private GridBagConstraints    restriciones;

    public DibujoFor(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                     VentanaEmergente _ventanaEmergente, List <Object> _variables){
        super(texto,lista,_contenedor,_restriciones,_ventanaEmergente,_variables);
        setPreferredSize(new Dimension(200, 500));

        this.restriciones         = new GridBagConstraints();
        this.restriciones.gridx   = 0;
        this.restriciones.gridy   = GridBagConstraints.RELATIVE; // Se inicia en la siguiente fila
        this.restriciones.anchor  = GridBagConstraints.CENTER; // Alineación vertical superior
        this.restriciones.fill    = GridBagConstraints.HORIZONTAL; // Llenar horizontalmente
        this.restriciones.weighty = 0; // No expandir en dirección vertical
        this.restriciones.insets  = new Insets(0, 0, 0, 0); // Sin espacio entre paneles

        this.setLayout(new BoxLayout(DibujoFor.this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.lista = new ArrayList<>();

        this.contenido = new DibujoForInterno(texto,0,0,0,this.variables,this);

        this.condicion = new DibujoForInicio(this.texto,lista,this,(DibujoForInterno) contenido,this.restriciones,this.ventanaEmergente,this.variables);
        this.fin = new DibujoForFin(texto,lista,this,this.restriciones,this.ventanaEmergente,this.variables);

        this.add(condicion,this.restriciones);
        this.add(contenido,this.restriciones);
        this.add(fin,this.restriciones);
    }

    public void modificarValores()
    {
        DibujoForInterno aux = (DibujoForInterno) this.contenido;
        aux.modificarValores();
    }

    public JPanel getContenido(){

        DibujoForInterno aux = (DibujoForInterno) this.contenido;

        return aux.getIzquierda();
    }

    public List<PanelPersonalizado> getLista() {

        DibujoForInterno aux = (DibujoForInterno) this.contenido;
        return aux.getListaIzquierda();
    }

    @Override
    public void ajustarSize()
    {
        DibujoForInterno aux = (DibujoForInterno) this.contenido;

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

    public int[] getIntervalo()
    {
        DibujoForInterno aux = (DibujoForInterno) this.contenido;

        return  aux.getIntervalo();
    }

    public void setIntervalo(int[] vals)
    {
        DibujoForInterno aux = (DibujoForInterno) this.contenido;

        aux.setIntervalo(vals);
    }

    @Override
    public String getTexto()
    {
        DibujoForInterno aux = (DibujoForInterno) this.contenido;

        return aux.getTexto();
    }
}
