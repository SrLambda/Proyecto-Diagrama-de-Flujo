package Dibujos.PanelesMovibles.Decision;

import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DibujoDecision;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DibujoDecisionInicio extends PanelPersonalizado {
    private DibujoDecision dibujoDecision;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);
    private String var1;
    private String condicion;
    private String var2;
    public DibujoDecisionInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, GridBagConstraints _restriciones,
                                VentanaEmergente _ventanaEmergente, List <Object> _variables) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        String nuevoTxt = quitarEspacios(texto);
        asignarVariable(nuevoTxt);
        manejoSalidas();

        addMouseListener(new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) { // Doble clic para editar el texto
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto); // Actualizar el texto de la figura
                    }
                }

            }
        });
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Coordenadas del rombo
        int x1 = (int) ((panelWidth / 4)+panelWidth*0.1);                    // Coordenada x del lado izquierdo del rectángulo
        int x2 = (int) ((panelWidth - (panelWidth / 4))-panelWidth*0.1);     // Coordenada x del lado derecho del rectángulo
        int y1 = (int) ((panelHeight / 4)+panelHeight*0.15);                 // Coordenada y del lado superior del rectángulo
        int y2 = (int) ((panelHeight - (panelHeight / 4))-panelHeight*0.15); // Coordenada y del lado inferior del rectángulo

        int centro_x = panelWidth/2;                                         // Centro horizontal
        int centro_y = panelHeight/2;                                        // Centro vertical

        int cuarto = panelWidth/4;


        // Dibujar las líneas que forman el rombo
        g.setColor(Color.BLACK);
        g.drawLine(x1, centro_y,centro_x, y1);     // Lado superior
        g.drawLine(x2, centro_y,centro_x, y1);     // Lado derecho
        g.drawLine(x1, centro_y,centro_x, y2);     // Lado inferior
        g.drawLine(x2, centro_y,centro_x, y2);     // Lado izquierdo

        // Dibujar flujo
        g.setColor(Color.BLACK);
        g.drawLine(centro_x,0,centro_x,y1);  // Linea superior

        g.drawLine(centro_x,y1,centro_x,y1);   // Flecha
        g.drawLine(centro_x,y1,centro_x,y1);   // de flujo

        g.drawLine(x1,centro_y,cuarto,centro_y);
        g.drawLine(x2,centro_y,cuarto*3,centro_y);
        g.drawLine(cuarto,centro_y,cuarto,panelHeight);
        g.drawLine(cuarto*3,centro_y,cuarto*3,panelHeight);

        // fuente con el tamaño especificado
        g.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
    }


    public boolean verVariable(String _entrada, String seleccion) {
        if(_entrada == null){
            return false;
        }
        while(!validarVariableTrueFalse(_entrada)){
            _entrada = JOptionPane.showInputDialog(null, "Variable ["+_entrada+"] no creada");
            if(_entrada == null){
                return false;
            }
        }
        if(seleccion.equals("var1")){
            this.setVar1(_entrada);
        }else{
            this.setVar2(_entrada);
        }
        return true;
    }

    public void asignarVariable(String _entrada){
        String opRegex = "(<=|>=|<|>|=|!)";
        Pattern pattern = Pattern.compile(opRegex);
        Matcher matcher = pattern.matcher(_entrada);

        while (!matcher.find() || _entrada.length() < 3) {
            _entrada = JOptionPane.showInputDialog(null, "Operador incorrecto", _entrada);
            if (_entrada == null) {
                this.texto = null;
                return;
            }
            _entrada = quitarEspacios(_entrada);
            matcher = pattern.matcher(_entrada);
        }

        int posOperador = matcher.start();
        this.setVar1(_entrada.substring(0, posOperador).trim());
        this.setCondicion(_entrada.substring(posOperador, posOperador + matcher.group().length()).trim());
        this.setVar2(_entrada.substring(posOperador + getCondicion().length()).trim());


        System.out.println("Var1: "+ this.getVar1());
        System.out.println("Var2: "+ this.getVar2());
        System.out.println("Operador: "+ this.getCondicion());
    }


    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public void manejoSalidas() {

        if (this.getVar1() == null) {
            System.out.println("Var1 null");
            actualizarTexto(null);
            return;
        }

        if (this.getCondicion() == null) {
            System.out.println("Condicion null");
            actualizarTexto(null);
            return;
        }

        if (this.getVar2() == null) {
            System.out.println("Var2 null");
            actualizarTexto(null);
            return;
        }


        if (!verVariable(this.getVar1(), "var1")) {
            actualizarTexto(null);
            return;
        }

        if (!verVariable(this.getVar2(), "var2")) {
            actualizarTexto(null);
            return;
        }

        this.texto = getVar1() + getCondicion() + getVar2();
        this.variables.set(this.indice, "Decision");
        this.variables.set(this.indice + 1, this.texto);
        this.indice += 2;


    }

    public void setDibujoDecision(DibujoDecision dibujoDecision) {
        this.dibujoDecision = dibujoDecision;
    }

    public void actualizarTexto(String nuevoTexto) {
        System.out.println("Cambiando texto: "+nuevoTexto);
        cambiarTexto(nuevoTexto);
    }

}
