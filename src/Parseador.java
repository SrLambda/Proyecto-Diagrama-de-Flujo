import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.*;

import java.util.List;

public class Parseador {
    private List<PanelPersonalizado> componentes;
    private StringBuilder pseudo_codigo;


    Parseador(List<PanelPersonalizado> _componenetes)
    {

        this.componentes = _componenetes;
        this.pseudo_codigo = new StringBuilder();


        // PSEUDO CODIGO INIT
        this.pseudo_codigo.append("INICIO\n");
        this.pseudo_codigo.append("FIN");

    }

    public void actualizar(){

        this.pseudo_codigo = new StringBuilder();

        this.pseudo_codigo.append("INICIO\n");

        this.pseudo_codigo.append(pseudoCodIntermedio(0,this.componentes));

        this.pseudo_codigo.append("FIN");
    }

    public String getPseuddoCodigo()
    {
        return this.pseudo_codigo.toString();
    }




    private String identificarComponente(PanelPersonalizado componente)
    {

        if(componente  instanceof DibujoProceso)
        {
            return "Proceso";
        }
        else if (componente instanceof DibujoEntrada)
        {
            return "Entrada";
        }
        else if (componente instanceof DibujoSalida)
        {
            return "Salida";
        }
        else if (componente instanceof DibujoDocumento)
        {
            return "Documento";
        }
        else if (componente instanceof DibujoDecision)
        {
            return "Decision";
        }
        else if (componente instanceof DibujoWhile)
        {
            return "While";
        }
        else if (componente instanceof DibujoFor)
        {
            return "For";
        }
        else
        {
            return "---" ;
        }

    }





    private String pseudoCodIntermedio(int tab,List<PanelPersonalizado> lista){

        StringBuilder cod_inter = new StringBuilder();

        for(PanelPersonalizado componente : lista){

            //Identificar componente
            String tipo = identificarComponente(componente);

            //Añadir componenete
            if(!tipo.equals("---"))
            {
                agregarLinea(tipo,componente,tab);
            }

        }

        return cod_inter.toString();
    }


    private void agregarLinea(String tipo,PanelPersonalizado componente,int tab){

        StringBuilder separacion = new StringBuilder();

        for (int i = 0; i < tab; i++) {
            separacion.append("\t");
        }


        switch (tipo){
            case "Proceso":

                this.pseudo_codigo.append(separacion).append("DECLARAR ==> ");
                this.pseudo_codigo.append(componente.getTexto());
                this.pseudo_codigo.append("\n");

                break;

            case "Entrada":

                this.pseudo_codigo.append(separacion).append("ENTRADA ==> ");
                this.pseudo_codigo.append(componente.getTexto());
                this.pseudo_codigo.append("\n");

                break;

            case "Salida":

                this.pseudo_codigo.append(separacion).append("SALIDA ==> ");
                this.pseudo_codigo.append(componente.getTexto());
                this.pseudo_codigo.append("\n");

                break;

            case "Documento":

                this.pseudo_codigo.append(separacion).append("IMPRIMIR ==> ");
                this.pseudo_codigo.append(componente.getTexto());
                this.pseudo_codigo.append("\n");

                break;

            case "Decision":

                DibujoDecision aux = (DibujoDecision) componente;

                // Condicion
                this.pseudo_codigo.append(separacion).append("SI (");
                this.pseudo_codigo.append(componente.getTexto());
                this.pseudo_codigo.append(")\n");

                // Verdad
                this.pseudo_codigo.append(separacion).append("{\n");

                String verdad = pseudoCodIntermedio(tab+1,aux.getVerdad());
                this.pseudo_codigo.append(verdad);

                this.pseudo_codigo.append(separacion).append("}\n");


                // Falso
                this.pseudo_codigo.append(separacion).append("ENTONCES\n");
                this.pseudo_codigo.append(separacion).append("{\n");

                String falso = pseudoCodIntermedio(tab+1,aux.getFalso());
                this.pseudo_codigo.append(falso);

                this.pseudo_codigo.append(separacion).append("}\n");
                break;

            case "While":

                DibujoWhile aux_w = (DibujoWhile) componente;

                this.pseudo_codigo.append(separacion).append("MIENTRAS ( ");
                this.pseudo_codigo.append(componente.getTexto());
                this.pseudo_codigo.append(" )\n");

                this.pseudo_codigo.append(separacion).append("{\n");

                String ciclo_while = pseudoCodIntermedio(tab+1,aux_w.getLista());
                this.pseudo_codigo.append(ciclo_while);

                this.pseudo_codigo.append(separacion).append("}\n");

                break;

            case "For":

                DibujoFor aux_f = (DibujoFor) componente;

                int[]  aux_val = aux_f.getIntervalo();
                String var     = aux_f.getTexto();

                this.pseudo_codigo.append(separacion).append("POR ( ");
                this.pseudo_codigo.append(var).append(" = ").append(aux_val[0]).append(" ; ");
                this.pseudo_codigo.append(var).append(" += ").append(aux_val[1]).append(" ; ");
                this.pseudo_codigo.append(var).append(" == ").append(aux_val[2]).append(" )\n");

                this.pseudo_codigo.append(separacion).append("{\n");

                String ciclo_for = pseudoCodIntermedio(tab+1,aux_f.getLista());
                this.pseudo_codigo.append(ciclo_for);

                this.pseudo_codigo.append(separacion).append("}\n");


                break;
        }

    }




}
