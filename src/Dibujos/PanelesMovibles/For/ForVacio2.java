package Dibujos.PanelesMovibles.For;

import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DibujoFor;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ForVacio2 extends PanelPersonalizado{

    private int v_inicial;
    private int v_final;
    private int incremento;
    private String var1S;
    private String valorIniS;
    private String valorFinS;
    private String increS;
    private DibujoFor dibujoFor;


    public ForVacio2(String _texto, List<PanelPersonalizado> lista, JPanel _contenedor, int _incremento , int _v_inicial, int _v_final,
                     GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente, List <Object> _variables, DibujoFor _dibujoFor) {
        super(_texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        this.dibujoFor = _dibujoFor;
        this.v_inicial  = _v_inicial;
        this.incremento = _incremento;
        this.v_final    = _v_final;


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    modificarValores();

                }
            }
        });

        //manejarSalidas();

    }


    public int getValorInicial()
    {
        return this.v_inicial;
    }

    public int getIncremento()
    {
        return this.incremento;
    }

    public int getValorFinal()
    {
        return this.v_final;
    }

    public String getVar1S() {
        return var1S;
    }

    public void setVar1S(String var1S) {
        this.var1S = var1S;
    }

    public String getValorIniS() {
        return valorIniS;
    }

    public void setValorIniS(String valorIniS) {
        this.valorIniS = valorIniS;
    }

    public String getValorFinS() {
        return valorFinS;
    }

    public void setValorFinS(String valorFinS) {
        this.valorFinS = valorFinS;
    }

    public String getIncreS() {
        return increS;
    }

    public void setIncreS(String increS) {
        this.increS = increS;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        int arcWidth = 100;
        int arcHeight = 100;
        int ancho = this.getWidth();
        int alto = this.getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRoundRect(ancho/4, 0, ancho/2, alto/2, arcWidth, arcHeight);

        g.setColor(Color.BLACK);
        g.drawLine(ancho/2,alto/2,ancho/2,alto);

        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, 30);
        String t_incremento = String.valueOf(incremento);
        String t_inicial = String.valueOf(v_inicial);
        String t_final = String.valueOf(v_final);
        g.drawString(t_inicial, ancho/2-30, 60);
        g.drawString(t_incremento, ancho/2, 60);
        g.drawString(t_final, ancho/2+30, 60);
    }

    public void modificarValores()
    {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Establecer el layout vertical
        JTextField variable = new JTextField(10);
        JTextField _inicial = new JTextField(10);
        JTextField _incremento = new JTextField(10);
        JTextField _final = new JTextField(10);

        panel.add(new JLabel("Variable:"));
        panel.add(variable);
        panel.add(new JLabel("Valor inicial:"));
        panel.add(_inicial);
        panel.add(new JLabel("Incremento/Decremento:"));
        panel.add(_incremento);
        panel.add(new JLabel("Valor final:"));
        panel.add(_final);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Editar ciclo", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Obtener los datos ingresados por el usuario
            String dato1 = variable.getText();
            String dato2 = _inicial.getText();
            String dato3 = _incremento.getText();
            String dato4 = _final.getText();
            texto = dato1;
            manejarSalidas(dato1, dato2, dato3, dato4);


        } else {
            System.out.println("Operación cancelada.");
        }

    }

    public void verVariable(String _entrada) {
        if(_entrada == null){
            return;
        }
        while(!validarVariableTrueFalse(_entrada)){
            _entrada = JOptionPane.showInputDialog(null, "Variable ["+_entrada+"] no creada");
            if(_entrada == null){
                return;
            }
        }
        setVar1S(_entrada);
    }

    @Override
    public String buscar(String _entrada) {
        if(_entrada == null || _entrada == ""){
            return null;
        }
        try {
            Integer.parseInt(_entrada);
            _entrada = validarMixto(validarEntero.validar(_entrada), _entrada);
            if(_entrada == null){
                return null;
            }
            return _entrada;
        } catch (NumberFormatException ex1) {
            return null;
        }
    }

    public void manejarSalidas(String dato1, String dato2, String dato3, String dato4){

        verVariable(dato1);
        if(this.getVar1S() == null){
            dibujoFor.setTexto(null);
            this.texto = null;
            return;
        }

        setValorIniS(buscar(dato2));
        if(this.getValorIniS() == null){
            dibujoFor.setTexto(null);
            this.texto = null;
            return;
        }

        setIncreS(buscar(dato3));

        if(this.getIncreS() == null){
            dibujoFor.setTexto(null);
            this.texto = null;
            return;
        }

        setValorFinS(buscar(dato4));

        if(this.getValorFinS() == null){
            dibujoFor.setTexto(null);
            this.texto = null;
            return;
        }

        String txtALter = getVar1S()+";"+getValorIniS()+";"+getValorFinS()+";"+getIncreS();
        this.variables.set(this.indice, "For");
        this.variables.set(this.indice + 1, txtALter);
        System.out.println("Guardando FOR en pos: "+this.indice+" con valor: "+txtALter);
        this.indice += 2;
    }

}