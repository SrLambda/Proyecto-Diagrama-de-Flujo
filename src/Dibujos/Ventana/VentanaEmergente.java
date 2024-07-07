package Dibujos.Ventana;

import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.*;
import Dibujos.RehacerDeshacer;
import Dibujos.PanelesNoMovibles.DibujoFin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class VentanaEmergente {

    private List<PanelPersonalizado> lista;
    private JPanel contenedor;
    private GridBagConstraints restricciones;

    private DefaultMutableTreeNode inicio;

    private static DefaultMutableTreeNode selecion;


    private boolean esPrincipal; //eliminar
    private List<String> compnentes; //eliminar

    public VentanaEmergente(List<PanelPersonalizado> _lista, JPanel _contenedor,GridBagConstraints _restricciones)
    {
        this.lista         =  _lista;
        this.contenedor    =  _contenedor;
        this.restricciones = _restricciones;

        this.inicio = new DefaultMutableTreeNode(new Seleccion(_lista,"AGREGAR FIN",_contenedor,null,-1));



        //eliminar
        this.compnentes    =  new ArrayList<>();
        this.esPrincipal   =  !_lista.isEmpty();
        this.actualizarComponentes(this.lista,this.contenedor,this.inicio,null);
    }

    public void actualizar()
    {
        this.actualizarComponentes(this.lista,this.contenedor,this.inicio,null);
    }

    private void actualizarComponentes(List<PanelPersonalizado> _lista, JPanel _contenedor,DefaultMutableTreeNode _root,PanelPersonalizado _padre)
    {

       _root.removeAllChildren();

        for (int i = 0; i < _lista.size(); i++)
        {
            StringBuilder seleccion = new StringBuilder();
            String texto_panel = _lista.get(i).getTexto();




            if(_lista.get(i) instanceof DibujoProceso)
            {


                seleccion.append(" Proceso -> ").append(texto_panel);

                _root.add(new DefaultMutableTreeNode(new Seleccion(_lista,seleccion.toString(),_contenedor,_padre,i)));


            }
            else if(_lista.get(i) instanceof DibujoEntrada)
            {


                seleccion.append(" Entrada -> ").append(texto_panel);

                _root.add(new DefaultMutableTreeNode(new Seleccion(_lista,seleccion.toString(),_contenedor,_padre,i)));


            }
            else if (_lista.get(i) instanceof DibujoSalida)
            {


                seleccion.append(" Salida -> ").append(texto_panel);

                _root.add(new DefaultMutableTreeNode(new Seleccion(_lista,seleccion.toString(),_contenedor,_padre,i)));


            }
            else if (_lista.get(i) instanceof DibujoDocumento)
            {


                seleccion.append(" Documento -> ").append(texto_panel);

                _root.add(new DefaultMutableTreeNode(new Seleccion(_lista,seleccion.toString(),_contenedor,_padre,i)));


            }
            else if (_lista.get(i) instanceof DibujoDecision)
            {


                seleccion.append(" Decision -> ").append(texto_panel);

                DefaultMutableTreeNode aux = new DefaultMutableTreeNode(new Seleccion(_lista,seleccion.toString(),_contenedor,_padre,i));
                _root.add(aux);

                DibujoDecision panel = (DibujoDecision) _lista.get(i);


                DefaultMutableTreeNode verd = new DefaultMutableTreeNode(new Seleccion(panel.getVerdad(),"Verdad",panel.getContVerdad(),panel,-1));
                DefaultMutableTreeNode fals = new DefaultMutableTreeNode(new Seleccion(panel.getFalso(),"Falso",panel.getContFalso(),panel,-1));

                aux.add(verd);
                aux.add(fals);

                this.actualizarComponentes(panel.getVerdad(),panel.getContVerdad(),verd,panel);
                this.actualizarComponentes(panel.getFalso(),panel.getContFalso(),fals,panel);


            }
            else if (_lista.get(i) instanceof DibujoWhile)
            {

                seleccion.append(" While -> ").append(texto_panel);

                DefaultMutableTreeNode aux = new DefaultMutableTreeNode(new Seleccion(_lista,seleccion.toString(),_contenedor,_padre,i));
                _root.add(aux);

                DibujoWhile panel = (DibujoWhile) _lista.get(i);


                DefaultMutableTreeNode cicl = new DefaultMutableTreeNode(new Seleccion(panel.getLista(),"Ciclo",panel.getContenido(),panel,-1));

                aux.add(cicl);

                this.actualizarComponentes(panel.getLista(),panel.getContenido(),cicl,panel);

            }
            else if (_lista.get(i) instanceof DibujoFor)
            {

                seleccion.append(" For -> ").append(texto_panel);

                DefaultMutableTreeNode aux = new DefaultMutableTreeNode(new Seleccion(_lista,seleccion.toString(),_contenedor,_padre,i));
                _root.add(aux);

                DibujoFor panel = (DibujoFor) _lista.get(i);


                DefaultMutableTreeNode cicl = new DefaultMutableTreeNode(new Seleccion(panel.getLista(),"Ciclo",panel.getContenido(),panel,-1));

                aux.add(cicl);

                this.actualizarComponentes(panel.getLista(),panel.getContenido(),cicl,panel);

            }
            else if (_lista.get(i) instanceof DibujoDoWhile)
            {
                seleccion.append(" DoWhile -> ").append(texto_panel);

                DefaultMutableTreeNode aux = new DefaultMutableTreeNode(new Seleccion(_lista,seleccion.toString(),_contenedor,_padre,i));
                _root.add(aux);

                DibujoDoWhile panel = (DibujoDoWhile) _lista.get(i);


                DefaultMutableTreeNode cicl = new DefaultMutableTreeNode(new Seleccion(panel.getLista(),"Ciclo",panel.getContenido(),panel,-1));

                aux.add(cicl);

                this.actualizarComponentes(panel.getLista(),panel.getContenido(),cicl,panel);

            }
        }
    }

    public void agregar(PanelPersonalizado nuevo, Frame front)

    {


        DefaultTreeModel treeModel = new DefaultTreeModel(this.inicio);

        // Crear el JTree con el modelo
        JTree tree = new JTree(treeModel);

        // Añadir un TreeSelectionListener para guardar la selección
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                selecion = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            }
        });


        JScrollPane treeScrollPane = new JScrollPane(tree);


        JDialog dialog = new JDialog(front, "Seleccionar Posición", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(300, 400);
        dialog.setLocationRelativeTo(null);
        dialog.getContentPane().add(treeScrollPane, BorderLayout.CENTER);


        JButton closeButton = new JButton("Agregar");

        closeButton.addActionListener(e -> {

            Seleccion aux = (Seleccion) selecion.getUserObject();


            if (selecion != null)
            {

                if(aux.getPosicion() == -1)
                {

                    agregarFinal(nuevo, aux);
                }
                else
                {
                    aux.getLista().add(aux.getPosicion(),nuevo);
                    aux.getContenedor().add(nuevo,this.restricciones,aux.getPosicion());
                }

            }
            else{

                agregarFinal(nuevo, aux);
            }

            //en caso de estar dentro de sub contenedores
            if (aux.getPadre() != null)
            {
                aux.getPadre().ajustarSize();

                if(aux.getPadre() instanceof DibujoDecision)
                {
                    DibujoDecision descAux = (DibujoDecision) aux.getPadre();
                    descAux.eliminarEspaciosEnBlanco();
                }
            }

            dialog.dispose();
        });


        dialog.getContentPane().add(closeButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void agregarFinal(PanelPersonalizado nuevo, Seleccion aux) {
        if(!aux.getLista().isEmpty() && aux.getLista().get(aux.getLista().size()-1) instanceof DibujoFin)
        {

            aux.getLista().add(aux.getLista().size()-1,nuevo);
            aux.getContenedor().add(nuevo,this.restricciones,aux.getLista().size()-2);

        }
        else
        {

            aux.getLista().add(nuevo);
            aux.getContenedor().add(nuevo,this.restricciones);

        }
    }


    private class Seleccion
    {
        private List<PanelPersonalizado> lista;
        private PanelPersonalizado padre;
        private String textoMostrar;
        private JPanel contenedor;
        private int posicion;

        Seleccion(List<PanelPersonalizado> _lista,String _tx,JPanel _contenedor,PanelPersonalizado _padre,int _posicion)
        {
            this.textoMostrar = _tx;
            this.contenedor   = _contenedor;
            this.posicion     = _posicion;
            this.lista        = _lista;
            this.padre        = _padre;
        }

        public List<PanelPersonalizado> getLista()
        {
            return lista;
        }

        public JPanel getContenedor() {
            return contenedor;
        }

        public int getPosicion()
        {
            return posicion;
        }

        public PanelPersonalizado getPadre()
        {
            return padre;
        }

        @Override
        public String toString()
        {
            return this.textoMostrar;
        }
    }
}
