import Dibujos.FactoryPanel;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DibujoDecision;
import Dibujos.PanelesMovibles.DibujoDoWhile;
import Dibujos.PanelesMovibles.DibujoFor;
import Dibujos.PanelesMovibles.DibujoWhile;
import Dibujos.PanelesNoMovibles.DibujoFin;
import Dibujos.PanelesNoMovibles.DibujoInicio;
import Dibujos.Ventana.VentanaEmergente;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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


    public void agregarMediantePseudocodigo(String _pseudocodigoInicial,Frame _front)
    {
        //Mostrar Pseudocodigo en una ventana emergente


        EntradaPseudocodigo ventana = new EntradaPseudocodigo(_front, "Pseudocodigo", _pseudocodigoInicial);
        ventana.setVisible(true);





        //Recojer texto editado de la ventana emergente

        this.pseudocodigo = ventana.getPseudocodigo();
        System.out.println(this.pseudocodigo);



        //Crear respaldo

        List<PanelPersonalizado> backup_paneles   = new ArrayList<>();
        List<Object>             backup_variables = new ArrayList<>();


        backup_paneles.addAll(this.lista);
        backup_variables.addAll(this.variables);






        //Transformar de texto a Diagrama

        String[] lineas = this.limpiarPsCod(this.pseudocodigo);

        try{

            this.contenedor.removeAll();
            this.lista.clear();
            this.variables.clear();

            Integer i = 0;

            armarDiagrama(lineas,this.contenedor,this.lista,i,lineas.length);



        }
        catch (Exception e)
        {

            this.mostrarError(e);


            // Utilizar respaldo

            this.contenedor.removeAll();
            this.lista.clear();
            this.variables.clear();

            for (int i = 0; i < backup_paneles.size(); i++)
            {

                this.lista.add(backup_paneles.get(i));

                this.contenedor.add(backup_paneles.get(i),this.restricciones);

            }

            backup_variables.addAll(this.variables);


        }

    }


    public void armarDiagrama(String[] lineas, JPanel _contenedor,List<PanelPersonalizado> _lista,Integer i, int fin){


        PanelPersonalizado panel_act;
        

        panel_act = this.identificarPanel(lineas[i],_lista,_contenedor);

        if(panel_act != null)
        {


            if(i == fin)
            {


                _contenedor.add(panel_act,this.restricciones);
                _lista.add(panel_act);

            }
            else if(panel_act instanceof DibujoDecision)
            {

                //Agregar
                _contenedor.add(panel_act,this.restricciones);
                _lista.add(panel_act);


                DibujoDecision aux = (DibujoDecision) panel_act;

                //Agregar en verdad
                i++;
                armarDiagrama(lineas,aux.getContVerdad(),aux.getVerdad(),i,fin);

                //Agregar en falso
                i++;
                armarDiagrama(lineas,aux.getContFalso(),aux.getFalso(),i,fin);

                //Avanzar
                i++;
                armarDiagrama(lineas,_contenedor,_lista,i,fin);

            }
            else if (panel_act instanceof DibujoWhile)
            {

                //Agregar
                _contenedor.add(panel_act,this.restricciones);
                _lista.add(panel_act);


                DibujoWhile aux = (DibujoWhile) panel_act;

                //Agregar dentro de bucle
                i++;
                armarDiagrama(lineas,aux.getContenido(),aux.getLista(),i,fin);

                //Avanzar
                i++;
                armarDiagrama(lineas,_contenedor,_lista,i,fin);

            }
            else if (panel_act instanceof DibujoFor)
            {

                //Agregar
                _contenedor.add(panel_act,this.restricciones);
                _lista.add(panel_act);


                DibujoFor aux = (DibujoFor) panel_act;

                //Agregar dentro de bucle
                i++;
                armarDiagrama(lineas,aux.getContenido(),aux.getLista(),i,fin);

                //Avanzar
                i++;
                armarDiagrama(lineas,_contenedor,_lista,i,fin);

            }
            else if ((panel_act instanceof DibujoDoWhile))
            {

                //Agregar
                _contenedor.add(panel_act,this.restricciones);
                _lista.add(panel_act);

                DibujoDoWhile aux = (DibujoDoWhile) panel_act;

                //Agregar dentro de bucle
                i++;
                armarDiagrama(lineas,aux.getContenido(),aux.getLista(),i,fin);

                //Ingresar texto en condición de ciclo
                DibujoDoWhile fn_ciclo = (DibujoDoWhile) this.identificarPanel(lineas[i],_lista,_contenedor);
                aux.setTexto(fn_ciclo.getTexto());

                //Avanzar
                i++;
                armarDiagrama(lineas,_contenedor,_lista,i,fin);

            }
            else
            {

                //Agregar
                _contenedor.add(panel_act,this.restricciones);
                _lista.add(panel_act);

                //Avanzar
                i++;
                armarDiagrama(lineas,_contenedor,_lista,i,fin);

            }

        }

        //Avanzar
        i++;
        armarDiagrama(lineas,_contenedor,_lista,i,fin);


    }



    public String[] limpiarPsCod(String _pseudocodigo)
    {

        String psdc_sin_tab  = _pseudocodigo.replace("\t", "");

        return psdc_sin_tab.split("\n");
    }


    private PanelPersonalizado identificarPanel(String psdc, List<PanelPersonalizado> _lista,JPanel _contenedor){

        FactoryPanel factoryPanel = new FactoryPanel();

        PanelPersonalizado panel;

        if(psdc.contains("==>"))
        {
            String[] psdc_div = psdc.split("\\s==>\\s", 2);

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

                String[] aux = psdc.split("PARA \\( | ; | \\)\\{| = | \\+= | == ");

                String tx  = aux[0];
                int[] vars = {Integer.parseInt(aux[1]),Integer.parseInt(aux[3]),Integer.parseInt(aux[5])};

                panel = factoryPanel.crearPanel("for",tx,_lista,_contenedor,this.restricciones,this.ventanaEmergente,this.variables);

                DibujoFor dbj_for = (DibujoFor) panel;

                dbj_for.setIntervalo(vars);

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
        else if(psdc.contains("INICIO") && _contenedor == this.contenedor)
        {
            panel = new DibujoInicio("Inicio",this.lista,this.contenedor,this.restricciones,this.ventanaEmergente,this.variables);

        }
        else if (psdc.contains("FIN") && _contenedor == this.contenedor)
        {
            panel = new DibujoFin("Fin",this.lista,this.contenedor,this.restricciones,this.ventanaEmergente,this.variables);
        }
        else
        {
            panel = factoryPanel.crearPanel("proceso",psdc,_lista,_contenedor,this.restricciones,this.ventanaEmergente,this.variables);
        }

        return panel;
    }


    private  void mostrarError(Exception e) {
        // Obtener el mensaje de la excepción
        String mensajeError = e.getMessage();

        // Mostrar el mensaje de error en una ven   tana de diálogo
        JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
