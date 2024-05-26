import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.*;
import Dibujos.PanelesNoMovibles.DibujoInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VentanaEmergente {

    private List<PanelPersonalizado> lista;
    private List<String> compnentes;
    private JPanel contenedor;
    private boolean esPrincipal;
    private GridBagConstraints restricciones;

    VentanaEmergente(List<PanelPersonalizado> _lista, JPanel _contenedor,GridBagConstraints _restricciones)
    {
        this.lista         =  _lista;
        this.contenedor    =  _contenedor;
        this.compnentes    =  new ArrayList<>();
        this.esPrincipal   =  !_lista.isEmpty();
        this.restricciones = _restricciones;

        this.actualizarCompnentes();
    }

    public void actualizarCompnentes()
    {

        this.compnentes.clear();
        this.compnentes.add("AGREGAR FINAL");

        StringBuilder opcion;
        String         texto;
        int           indice;


        for (int i=0 ; i < this.lista.size() ; i++ ) {

            if(this.esPrincipal)
            {
                indice = i;
            }
            else
            {
                indice = i+1;
            }



            opcion = new StringBuilder();
            texto  = this.lista.get(i).getTexto();




           if(this.lista.get(i) instanceof DibujoProceso)
           {


               opcion.append(indice).append(" | PROCESO ");

               for (int j = 0; (j < 5) && (j < texto.length() ); j++)
               {
                   opcion.append(texto.charAt(j));
               }

               this.compnentes.add(opcion.toString());



           }
           else if(this.lista.get(i) instanceof DibujoEntrada)
           {


               opcion.append(indice).append(" | ENTRADA ");

               for (int j = 0; (j < 5) && (j < texto.length() ); j++)
               {
                   opcion.append(texto.charAt(j));
               }

               this.compnentes.add(opcion.toString());


           }
           else if (this.lista.get(i) instanceof DibujoSalida)
           {


               opcion.append(indice).append(" | SALIDA ");

               for (int j = 0; (j < 5) && (j < texto.length() ); j++)
               {
                   opcion.append(texto.charAt(j));
               }

               this.compnentes.add(opcion.toString());


           }
           else if (this.lista.get(i) instanceof DibujoDocumento)
           {


               opcion.append(indice).append(" | DOCUMENTO ");

               for (int j = 0; (j < 5) && (j < texto.length() ); j++)
               {
                   opcion.append(texto.charAt(j));
               }

               this.compnentes.add(opcion.toString());


           }
           else if (this.lista.get(i) instanceof DibujoDecision)
           {


               opcion.append(indice).append(" | DECICION ");

               for (int j = 0; (j < 5) && (j < texto.length() ); j++)
               {
                   opcion.append(texto.charAt(j));
               }

               this.compnentes.add(opcion.toString());

               this.compnentes.add(indice + " -- | INGRESAR DENTRO DE VERDAD | --");
               this.compnentes.add(indice + " -- | INGRESAR DENTRO DE FALSO  | --");


           }
           else if (this.lista.get(i) instanceof DibujoWhile)
           {

               opcion.append(indice).append(" | WHILE ");

               for (int j = 0; (j < 5) && (j < texto.length() ); j++)
               {
                   opcion.append(texto.charAt(j));
               }

               this.compnentes.add(opcion.toString());

               this.compnentes.add(indice + " -- | INGRESAR DENTRO DE CICLO | --");

           }
           else if (this.lista.get(i) instanceof DibujoFor)
           {

               opcion.append(indice).append(" | FOR ");

               for (int j = 0; (j < 5) && (j < texto.length() ); j++)
               {
                   opcion.append(texto.charAt(j));
               }

               this.compnentes.add(opcion.toString());

               this.compnentes.add(indice + " -- | INGRESAR DENTRO DE CICLO | --");

           }
           else if (this.lista.get(i) instanceof DibujoDoWhile)
           {
               opcion.append(indice).append(" | DO-WHILE ");

               for (int j = 0; (j < 5) && (j < texto.length() ); j++)
               {
                   opcion.append(texto.charAt(j));
               }

               this.compnentes.add(opcion.toString());

               this.compnentes.add(indice + " -- | INGRESAR DENTRO DEL CICLO | --");

           }


        }

    }

    public void agregar(PanelPersonalizado nuevo)
    {
        String seleccion = new String();

        String[] componentes_aux = new String[this.compnentes.size()];
        componentes_aux = this.compnentes.toArray(componentes_aux);

        JComboBox<String> comboBox = new JComboBox<>(componentes_aux);

        JButton botonAgregar = new JButton("Agregar");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alinear los botones a la derecha
        panelBotones.add(botonAgregar);

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ((Window) SwingUtilities.getRoot(panelBotones)).dispose();
                agregarElemento((String) comboBox.getSelectedItem(),nuevo);

            }

        });

        // Crear un panel para el contenido de la ventana emergente
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());
        panelContenido.add(comboBox, BorderLayout.CENTER);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);

        // Mostrar la ventana emergente
        int resultado = JOptionPane.showOptionDialog(null, panelContenido, "Agregar en lugar en especifico",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);

    }

    private void agregarElemento(String seleccion, PanelPersonalizado nuevo)
    {
        int posicion;


        if(seleccion.equals("AGREGAR FINAL"))
        {

            if(this.lista.isEmpty())
            {
                this.lista.add(nuevo);
                this.contenedor.add(nuevo,this.restricciones);
            }
            else
            {
                posicion = this.lista.size()-1;

                this.lista.add(posicion, nuevo);
                this.contenedor.add(nuevo,this.restricciones,posicion);
            }



        }
        else
        {


            String[] sel_desarmada = seleccion.split(" ");
            posicion = Integer.parseInt(sel_desarmada[0]);



            if(sel_desarmada[1].equals("--"))
            {


                if(sel_desarmada[6].equals("VERDAD"))
                {

                    DibujoDecision aux = (DibujoDecision) lista.get(posicion);

                    List<PanelPersonalizado> lista_aux = aux.getVerdad();
                    JPanel                    cont_aux = aux.getContVerdad();

                    VentanaEmergente vent_aux = new VentanaEmergente(lista_aux,cont_aux,this.restricciones);

                    nuevo.actualizarContenedor(lista_aux,cont_aux);
                    vent_aux.agregar(nuevo);

                    aux.eliminarEspaciosEnBlanco();
                    aux.ajustarSize();


                }
                else if(sel_desarmada[6].equals("CICLO"))
                {

                    if(lista.get(posicion) instanceof DibujoWhile)
                    {

                        DibujoWhile aux = (DibujoWhile) lista.get(posicion);

                        List<PanelPersonalizado> lista_aux = aux.getLista();
                        JPanel                    cont_aux = aux.getContenido();

                        VentanaEmergente vent_aux = new VentanaEmergente(lista_aux,cont_aux,this.restricciones);

                        nuevo.actualizarContenedor(lista_aux,cont_aux);
                        vent_aux.agregar(nuevo);
                        aux.ajustarSize();

                    }
                    else if (lista.get(posicion) instanceof DibujoFor)
                    {

                        DibujoFor aux = (DibujoFor) lista.get(posicion);

                        List<PanelPersonalizado> lista_aux = aux.getLista();
                        JPanel                    cont_aux = aux.getContenido();

                        VentanaEmergente vent_aux = new VentanaEmergente(lista_aux,cont_aux,this.restricciones);

                        nuevo.actualizarContenedor(lista_aux,cont_aux);
                        vent_aux.agregar(nuevo);
                        aux.ajustarSize();
                    }
                    else if (lista.get(posicion) instanceof DibujoDoWhile)
                    {
                        /*
                        DibujoDoWhile aux = (DibujoDoWhile) lista.get(posicion);

                        List <PanelPersonalizado> lista_aux = aux.getLista();
                        JPanel                     cont_aux = aux.getContenido();

                        VentanaEmergente vent_aux = new VentanaEmergente(lista_aux, cont_aux, this.restricciones);

                        nuevo.actualizarContenedor(lista_aux, cont_aux);
                        vent_aux.agregar(nuevo);
                        aux.ajustarSize();*/
                    }

                }
                else
                {
                    DibujoDecision aux = (DibujoDecision) lista.get(posicion);

                    List<PanelPersonalizado> lista_aux = aux.getFalso();
                    JPanel                    cont_aux = aux.getContFalso();

                    VentanaEmergente vent_aux = new VentanaEmergente(lista_aux,cont_aux,this.restricciones);

                    nuevo.actualizarContenedor(lista_aux,cont_aux);
                    vent_aux.agregar(nuevo);

                    aux.eliminarEspaciosEnBlanco();
                    aux.ajustarSize();
                }




            }
            else
            {

                if(!this.esPrincipal)
                {
                    posicion--;
                }

                this.lista.add(posicion, nuevo);
                this.contenedor.add(nuevo,this.restricciones,posicion);

            }
        }

    }

    public void mostrar(){
        for (String opcion: this.compnentes) {
            System.out.println(opcion);
        }
    }
}
