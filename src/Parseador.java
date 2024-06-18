import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.*;

import java.util.List;

public class Parseador {

    private List<PanelPersonalizado> componentes;
    private StringBuilder            pseudo_codigo;
    private StringBuilder            cod_phython;


    Parseador(List<PanelPersonalizado> _componenetes)
    {

        this.componentes   = _componenetes;
        this.pseudo_codigo = new StringBuilder();
        this.cod_phython   = new StringBuilder();


        // PSEUDO CODIGO INIT
        this.pseudo_codigo.append("INICIO\n");
        this.pseudo_codigo.append("FIN");

    }

    public void actualizar(){

        this.pseudo_codigo = new StringBuilder();

        this.pseudo_codigo.append("INICIO\n");

        this.pseudoCodIntermedio(0,this.componentes);

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
        else if (componente instanceof DibujoDoWhile)
        {
            return "Do-While";
        }
        else
        {
            return "---" ;
        }

    }





    private void pseudoCodIntermedio(int tab,List<PanelPersonalizado> lista){

        for(PanelPersonalizado componente : lista){

            //Identificar componente
            String tipo = identificarComponente(componente);

            //Añadir componenete
            if(!tipo.equals("---"))
            {
                agregarLinea(tipo,componente,tab);
            }

        }

    }

    // PSEUDOCODIGO


    private void agregarLinea(String tipo,PanelPersonalizado componente,int tab){

        StringBuilder separacion = new StringBuilder();

        for (int i = 0; i < tab; i++) {
            separacion.append("\t");
        }


        switch (tipo){
            case "Proceso":

                this.pseudo_codigo.append(separacion);
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
                this.pseudo_codigo.append(separacion).append("SI ( ");
                this.pseudo_codigo.append(componente.getTexto());
                this.pseudo_codigo.append(" )\n");

                // Verdad
                this.pseudo_codigo.append(separacion).append("{\n");

                pseudoCodIntermedio(tab+1,aux.getVerdad());


                this.pseudo_codigo.append(separacion).append("}\n");


                // Falso
                this.pseudo_codigo.append(separacion).append("ENTONCES\n");
                this.pseudo_codigo.append(separacion).append("{\n");

                pseudoCodIntermedio(tab+1,aux.getFalso());

                this.pseudo_codigo.append(separacion).append("}\n");
                break;

            case "While":

                DibujoWhile aux_w = (DibujoWhile) componente;

                this.pseudo_codigo.append(separacion).append("MIENTRAS ( ");
                this.pseudo_codigo.append(componente.getTexto());
                this.pseudo_codigo.append(" )\n");

                this.pseudo_codigo.append(separacion).append("{\n");

                pseudoCodIntermedio(tab+1,aux_w.getLista());


                this.pseudo_codigo.append(separacion).append("}\n");

                break;

            case "For":

                DibujoFor aux_f = (DibujoFor) componente;

                int[]  aux_val = aux_f.getIntervalo();
                String var     = aux_f.getTexto();

                this.pseudo_codigo.append(separacion).append("PARA ( ");
                this.pseudo_codigo.append(var).append(" = ").append(aux_val[0]).append(" ; ");
                this.pseudo_codigo.append(var).append(" += ").append(aux_val[1]).append(" ; ");
                this.pseudo_codigo.append(var).append(" == ").append(aux_val[2]).append(" )\n");

                this.pseudo_codigo.append(separacion).append("{\n");

                pseudoCodIntermedio(tab+1,aux_f.getLista());

                this.pseudo_codigo.append(separacion).append("}\n");


                break;

            case "Do-While":

                DibujoDoWhile aux_dw = (DibujoDoWhile) componente;

                this.pseudo_codigo.append(separacion).append("HACER\n");


                this.pseudo_codigo.append(separacion).append("{\n");

                pseudoCodIntermedio(tab+1,aux_dw.getLista());


                this.pseudo_codigo.append(separacion).append("}\n");

                this.pseudo_codigo.append(separacion).append("MIENTRAS ( ");
                this.pseudo_codigo.append(componente.getTexto());
                this.pseudo_codigo.append(" )\n");

                break;
        }
    }



    //Ejecutable

    public String generarEjecutable()
    {
        this.cod_phython   = new StringBuilder();

        this.codigo(0,this.componentes);

        return  this.cod_phython.toString();
    }


    public void codigo(int tab,List<PanelPersonalizado> lista)
    {

        for(PanelPersonalizado componente : lista){

            //Identificar componente
            String tipo = identificarComponente(componente);

            //Añadir componenete
            if(!tipo.equals("---"))
            {
                agregarInstruccion(tipo,componente,tab);
            }

        }

    }


    public void agregarInstruccion(String tipo,PanelPersonalizado componente,int tab)
    {

        StringBuilder separacion = new StringBuilder();

        for (int i = 0; i < tab; i++) {
            separacion.append("\t");
        }


        switch (tipo){
            case "Proceso":

                this.cod_phython.append(separacion);
                //Hay que modificar
                this.cod_phython.append(componente.getTexto());

                this.cod_phython.append("\n");

                break;

            case "Entrada":

                this.cod_phython.append(separacion).append(componente.getTexto());
                this.cod_phython.append(" = funciones.entrada()");
                this.cod_phython.append("\n");

                break;

            case "Salida":

                this.cod_phython.append(separacion).append("funciones.salida(");
                this.cod_phython.append(componente.getTexto()).append(")");
                this.cod_phython.append("\n");

                break;

            case "Documento":

                this.cod_phython.append(separacion).append("funciones.documento(");
                this.cod_phython.append(componente.getTexto()).append(")");
                this.cod_phython.append("\n");

                break;

            case "Decision":

                DibujoDecision aux = (DibujoDecision) componente;

                // Condicion
                this.cod_phython.append(separacion).append("if ");
                this.cod_phython.append(componente.getTexto());
                this.cod_phython.append(" :\n");

                //Verdad
                this.cod_phython.append(separacion).append("\tprint(\"verdad\"\n");
                pseudoCodIntermedio(tab+1,aux.getVerdad());


                // Falso
                this.cod_phython.append(separacion).append("else:\n");

                this.cod_phython.append(separacion).append("\tprint(\"falso\"\n");
                pseudoCodIntermedio(tab+1,aux.getFalso());


                break;

            case "While":

                DibujoWhile aux_w = (DibujoWhile) componente;

                this.cod_phython.append(separacion).append("MIENTRAS ( ");
                this.cod_phython.append(componente.getTexto());
                this.cod_phython.append(" )\n");

                this.cod_phython.append(separacion).append("{\n");

                pseudoCodIntermedio(tab+1,aux_w.getLista());


                this.cod_phython.append(separacion).append("}\n");

                break;

            case "For":

                DibujoFor aux_f = (DibujoFor) componente;

                int[]  aux_val = aux_f.getIntervalo();
                String var     = aux_f.getTexto();

                this.cod_phython.append(separacion).append("PARA ( ");
                this.cod_phython.append(var).append(" = ").append(aux_val[0]).append(" ; ");
                this.cod_phython.append(var).append(" += ").append(aux_val[1]).append(" ; ");
                this.cod_phython.append(var).append(" == ").append(aux_val[2]).append(" )\n");

                this.cod_phython.append(separacion).append("{\n");

                pseudoCodIntermedio(tab+1,aux_f.getLista());

                this.cod_phython.append(separacion).append("}\n");


                break;

            case "Do-While":

                DibujoDoWhile aux_dw = (DibujoDoWhile) componente;

                this.cod_phython.append(separacion).append("HACER\n");


                this.cod_phython.append(separacion).append("{\n");

                pseudoCodIntermedio(tab+1,aux_dw.getLista());


                this.cod_phython.append(separacion).append("}\n");

                this.cod_phython.append(separacion).append("MIENTRAS ( ");
                this.cod_phython.append(componente.getTexto());
                this.cod_phython.append(" )\n");

                break;
        }
    }
}
