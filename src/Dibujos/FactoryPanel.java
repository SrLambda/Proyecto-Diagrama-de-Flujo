package Dibujos;

import Dibujos.PanelesMovibles.*;

import javax.swing.*;
import java.util.List;

public class FactoryPanel {
    public PanelMovible crearPanel(String tipo, String texto, List<PanelPersonalizado> lista, JPanel _contenedor){

        if(tipo.equalsIgnoreCase("decision"))
        {
            return new DibujoDecision(texto,lista,_contenedor);
        }
        else if(tipo.equalsIgnoreCase("documento"))
        {
            return new DibujoDocumento(texto,lista,_contenedor);
        }
        else if(tipo.equalsIgnoreCase("entrada"))
        {
            return new DibujoEntrada(texto,lista,_contenedor);
        }
        else if(tipo.equalsIgnoreCase("for"))
        {
            return new DibujoFor(texto,lista,_contenedor);
        }
        else if (tipo.equalsIgnoreCase("salida"))
        {
            return new DibujoSalida(texto,lista,_contenedor);
        }
        else if(tipo.equalsIgnoreCase("proceso"))
        {
            return new DibujoProceso(texto,lista,_contenedor);
        }
        else if(tipo.equalsIgnoreCase("while"))
        {
            return new DibujoWhile(texto, lista, _contenedor);
        }
        else
        {
            throw new IllegalArgumentException("Tipo de figura desconocida.");
        }
    }
}
