package Dibujos;

import Dibujos.PanelesMovibles.*;
import Mapa.Mapa;

import javax.swing.*;
import java.util.List;

public class FactoryPanel {
    public PanelMovible crearPanel(String tipo, String texto, List<PanelPersonalizado> lista, JPanel _contenedor, Mapa _mapa){

        if(tipo.equalsIgnoreCase("decision"))
        {
            return new DibujoDecision(texto,lista,_contenedor,_mapa);
        }
        else if(tipo.equalsIgnoreCase("documento"))
        {
            return new DibujoDocumento(texto,lista,_contenedor,_mapa);
        }
        else if(tipo.equalsIgnoreCase("entrada"))
        {
            return new DibujoEntrada(texto,lista,_contenedor,_mapa);
        }
        else if(tipo.equalsIgnoreCase("for"))
        {
            return new InstruccionFor(texto,lista,_contenedor,_mapa);
        }
        else if (tipo.equalsIgnoreCase("salida"))
        {
            return new DibujoSalida(texto,lista,_contenedor,_mapa);
        }
        else if(tipo.equalsIgnoreCase("proceso"))
        {
            return new DibujoProceso(texto,lista,_contenedor,_mapa);
        }
        else
        {
            throw new IllegalArgumentException("Tipo de figura desconocida.");
        }

    }
}
