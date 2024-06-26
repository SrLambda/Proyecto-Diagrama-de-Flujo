import Dibujos.FactoryPanel;
import Dibujos.PanelPersonalizado;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Pseudocodigo {

    private String                   pseudocodigo;
    private JPanel                   contenedor;
    private List<PanelPersonalizado> lista;
    private GridBagConstraints       restricciones;
    private VentanaEmergente         ventanaEmergente;
    private List<Object>             variables;

    Pseudocodigo(String _pseudocodigo,JPanel _contenedor
                ,List<PanelPersonalizado> _lista, GridBagConstraints _restricciones,
                 VentanaEmergente _ventanaEmergente,List<Object> _variables)
    {
        this.pseudocodigo     = _pseudocodigo;
        this.contenedor       = _contenedor;
        this.lista            = _lista;
        this.restricciones    = _restricciones;
        this.ventanaEmergente = _ventanaEmergente;
        this.variables        = _variables;
    }

    public String[] limpiarPC(String _pseudocodigo)
    {

        String psdc_sin_tab  = _pseudocodigo.replace("\t", "");

        return psdc_sin_tab.split("\n");
    }


    public PanelPersonalizado identificarPanel(String psdc, List<PanelPersonalizado> _lista,JPanel _contenedor){

        FactoryPanel factoryPanel = new FactoryPanel();

        PanelPersonalizado panel;

        if(psdc.contains("==>"))
        {
            String[] psdc_div = psdc.split(" ==> ", 2);

            panel = factoryPanel.crearPanel(psdc_div[0],psdc_div[1],_lista,_contenedor,this.restricciones,this.ventanaEmergente,this.variables);

        }
        else if (psdc.contains("{"))
        {
            if(psdc.contains("MIENTRAS"))
            {
                String tx = psdc.replace("MIENTRAS ( ", "");

                StringBuilder tx_f = new StringBuilder();

                for (int i = 0; i < tx.length() - 2; i++) {
                    tx_f.append(tx.charAt(i));
                }

                panel = factoryPanel.crearPanel("while",tx_f.toString(),_lista,_contenedor,this.restricciones,this.ventanaEmergente,this.variables);



            }
            else if (psdc.contains("HACER"))
            {

                panel = factoryPanel.crearPanel("do-while","",_lista,_contenedor,this.restricciones,this.ventanaEmergente,this.variables);

            }
            else
            {

            }

        }
        else if (psdc.contains("MIENTRAS")) {
            String tx = psdc.replace("MIENTRAS ( ", "");

            StringBuilder tx_f = new StringBuilder();

            for (int i = 0; i < tx.length() - 1; i++) {
                tx_f.append(tx.charAt(i));
            }

            panel = factoryPanel.crearPanel("do-while",tx_f.toString(),_lista,_contenedor,this.restricciones,this.ventanaEmergente,this.variables);
        }
        else if (psdc.contains("}"))
        {
            panel = null;
        }
        else
        {
            panel = factoryPanel.crearPanel("proceso",psdc,_lista,_contenedor,this.restricciones,this.ventanaEmergente,this.variables);
        }

        return panel;
    }


}
