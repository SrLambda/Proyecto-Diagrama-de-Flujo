package Dibujos.PanelesMovibles;

import Dibujos.PanelMovible;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DoWhile.DibujoDoWhileFin;
import Dibujos.PanelesMovibles.DoWhile.DibujoDoWhileInicio;
import Dibujos.PanelesMovibles.DoWhile.DibujoDoWhileInterno;
import Dibujos.PanelesMovibles.While.DibujoWhileInterno;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DibujoDoWhile extends PanelMovible {

    private JPanel condicion;
    private JPanel contenido;
    private JPanel fin;

    private GridBagConstraints restriciones;
    private List<PanelPersonalizado> lista;

    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);

    public DibujoDoWhile(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                         VentanaEmergente _ventanaEmergente, Map<String, Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        setPreferredSize(new Dimension(200, 500));

        this.setLayout(new BoxLayout(DibujoDoWhile.this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.lista = new ArrayList<>();

        this.condicion = new DibujoDoWhileInicio(this.texto,lista,this,(DibujoDoWhileInterno) contenido, this.restriciones,
                this.ventanaEmergente,this.variables);
        this.contenido = new DibujoDoWhileInterno();
        this.fin = new DibujoDoWhileFin(this.texto,lista,this,(DibujoDoWhileInterno) contenido, this.restriciones,
                this.ventanaEmergente,this.variables);

        this.add(condicion,this.restriciones);
        this.add(contenido,this.restriciones);
        this.add(fin,this.restriciones);
    }

    public JPanel getContenido()
    {
        DibujoDoWhileInterno aux = (DibujoDoWhileInterno) this.contenido;
        return aux.getVerdadero2();
    }

    public List<PanelPersonalizado> getLista() {
        DibujoDoWhileInterno aux = (DibujoDoWhileInterno) this.contenido;
        return aux.getListaVerdadera2();
    }

    public void ajustarSize()
    {
        DibujoDoWhileInterno aux = (DibujoDoWhileInterno) this.contenido;

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
}
