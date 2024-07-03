package Dibujos.PanelesMovibles.While;
import Dibujos.PanelPersonalizado;
import Dibujos.PanelesMovibles.DibujoWhile;
import Dibujos.Ventana.VentanaEmergente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DibujoWhileInicio extends PanelPersonalizado {
    private DibujoWhileInterno interno;
    private List<PanelPersonalizado> panelesCiclo;
    protected Font textoFont = new Font("Serif", Font.PLAIN, 20);
    private String var1;
    private String condicion;
    private String var2;
    private DibujoWhile dibujoWhile;

    public DibujoWhileInicio(String texto, List<PanelPersonalizado> lista, JPanel _contenedor, DibujoWhileInterno _interno,
                             GridBagConstraints _restriciones, VentanaEmergente _ventanaEmergente, List <Object> _variables,
                             DibujoWhile _while) {
        super(texto, lista, _contenedor,_restriciones,_ventanaEmergente,_variables);
        this.interno = _interno;
        this.dibujoWhile = _while;
        panelesCiclo = lista;
        String nuevoTxt = quitarEspacios(texto);
        asignarVariable(nuevoTxt);
        manejoSalidas();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic para editar el texto
                    String nuevoTexto = JOptionPane.showInputDialog(null, "Editar texto:", texto);
                    if (nuevoTexto != null && !nuevoTexto.isEmpty()) {
                        cambiarTexto(nuevoTexto);
                    }
                }

            }
        });
    }

    public List<PanelPersonalizado> getPanelesCiclo() {
        return panelesCiclo;
    }

    public String getVar1() {
        return this.var1;
    }

    public void setVar1(String _var1) {
        this.var1 = _var1;
    }

    public String getCondicion() {
        return this.condicion;
    }

    public void setCondicion(String _condicion) {
        this.condicion = _condicion;
    }

    public String getVar2() {
        return this.var2;
    }

    public void setVar2(String _var2) {
        this.var2 = _var2;
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
        g.drawLine(centro_x,y2,centro_x,panelHeight);     // Linea inferior
        g.drawLine(x2,centro_y, (int) (cuarto*3.33),centro_y); //  Linea horizontal derecha
        g.drawLine((int) (panelWidth*0.1665),(int) (panelHeight*0.25),centro_x,(int) (panelHeight*0.25)); //  Linea horizontal izquierda

        g.drawLine((int) (cuarto*3.33),centro_y,(int) (cuarto*3.33),panelHeight); //  Linea vertical derecha
        g.drawLine((int) (panelWidth*0.1665),(int) (panelHeight*0.25),(int) (panelWidth*0.1665),panelHeight); //  Linea vertical izquierda

        g.drawLine(centro_x,y1,centro_x+10,y1-10);    //  Flecha
        g.drawLine(centro_x,y1,centro_x-10,y1-10);    //  de flujo

        // fuente con el tamaño especificado
        g.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(texto, x, y);
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
    }

    public void manejoSalidas(){

        if (this.getVar1() == null) {
            dibujoWhile.setTexto(null);
            this.texto = null;
            return;
        }



        if (this.getCondicion() == null) {
            dibujoWhile.setTexto(null);
            this.texto = null;
            return;
        }

        if (this.getVar2() == null) {
            dibujoWhile.setTexto(null);
            this.texto = null;
            return;
        }

        this.setVar1(buscar(this.getVar1()));

        if(this.getVar1() == null){
            dibujoWhile.setTexto(null);
            this.texto = null;
            return;
        }

        this.setVar2(buscar(this.getVar2()));

        if(this.getVar2() == null){
            dibujoWhile.setTexto(null);
            this.texto = null;
            return;
        }

        this.texto = getVar1() + getCondicion() + getVar2();
        indice1 = indice1 + 1;
        dibujoWhile.setTipo("While"+indice1);
        this.setTipo("While"+indice1);
        this.variables.add("While"+indice1);
        this.variables.add(this.texto);
    }
}
