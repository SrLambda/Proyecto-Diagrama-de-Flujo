package Dibujos;

import Dibujos.PanelesMovibles.*;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class FactoryPanel {
    public PanelMovible crearPanel(String tipo, String texto, List<PanelPersonalizado> lista, JPanel _contenedor,
                                   GridBagConstraints restricciones, VentanaEmergente _ventana, List <Object> _variables){

        if(tipo.equalsIgnoreCase("decision"))
        {
            return new DibujoDecision(texto,lista,_contenedor,restricciones,_ventana,_variables);
        }
        else if(tipo.equalsIgnoreCase("documento"))
        {
            return new DibujoDocumento(texto,lista,_contenedor,restricciones,_ventana,_variables);
        }
        else if(tipo.equalsIgnoreCase("entrada"))
        {
            return new DibujoEntrada(texto,lista,_contenedor,restricciones,_ventana,_variables);
        }
        else if (tipo.equalsIgnoreCase("salida"))
        {
            return new DibujoSalida(texto,lista,_contenedor,restricciones,_ventana,_variables);
        }
        else if(tipo.equalsIgnoreCase("proceso"))
        {
            return new DibujoProceso(texto,lista,_contenedor,restricciones,_ventana,_variables);
        }
        else if (tipo.equalsIgnoreCase("for")){
            return new DibujoFor(texto,lista,_contenedor,restricciones,_ventana,_variables);
        }
        else if(tipo.equalsIgnoreCase("while"))
        {
            return new DibujoWhile(texto, lista, _contenedor,restricciones,_ventana,_variables);
        }
        else if(tipo.equalsIgnoreCase("do-while"))
        {
            return new DibujoDoWhile(texto, lista, _contenedor,restricciones,_ventana,_variables);
        }
        else
        {
            throw new IllegalArgumentException("Tipo de figura desconocida.");
        }
    }
}
