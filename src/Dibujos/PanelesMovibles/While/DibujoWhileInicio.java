package Dibujos.PanelesMovibles.While;
import Dibujos.PanelMovible;
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

public class DibujoWhileInicio extends PanelMovible {
    private DibujoWhileInterno interno;
    private List<PanelPersonalizado> panelesCiclo;
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
        setPreferredSize(new Dimension(600, 500));
        manejoSalidas(texto);

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
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomFactor, zoomFactor);

        int widthTx  = this.anchoAlto[0];
        int heightTx = this.anchoAlto[1];

        int panelWidth  = getWidth();
        int panelHeight = getHeight();

        int centro_x = panelWidth/2;  // Centro horizontal
        int centro_y = panelHeight/2; // Centro vertical

        double anguloY = Math.PI / 6;
        double anguloX = Math.PI - anguloY;

        double z1 = Math.sqrt( (widthTx*widthTx)   / (2 - (2 * Math.cos( anguloX ) ) ) );
        double z2 = Math.sqrt( (heightTx*heightTx) / (2 - (2 * Math.cos( anguloY ) ) ) );

        double z = z1+z2;

        int deltX = (int) (z * Math.cos( anguloY / 2));
        int deltY = (int) (z * Math.sin( anguloY / 2));

        // Coordenadas del rombo
        int x1 = centro_x - deltX; // Coordenada x del lado izquierdo del rectángulo
        int x2 = centro_x + deltX; // Coordenada x del lado derecho del rectángulo
        int y1 = centro_y - deltY; // Coordenada y del lado superior del rectángulo
        int y2 = centro_y + deltY; // Coordenada y del lado inferior del rectángulo

        // Dibujar las líneas que forman el rombo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1, centro_y,centro_x, y1);     // Lado superior
        g2d.drawLine(x2, centro_y,centro_x, y1);     // Lado derecho
        g2d.drawLine(x1, centro_y,centro_x, y2);     // Lado inferior
        g2d.drawLine(x2, centro_y,centro_x, y2);     // Lado izquierdo

        // Dibujar flujo
        g2d.setColor(Color.BLACK);
        g2d.drawLine(centro_x,0,centro_x,y1);  // Linea superior
        g2d.drawLine(centro_x,y2,centro_x,panelHeight);     // Linea inferior
        g2d.drawLine(x2,centro_y, panelWidth - 100,centro_y); //  Linea horizontal derecha
        g2d.drawLine(100,(int) (panelHeight*0.25),centro_x,(int) (panelHeight*0.25)); //  Linea horizontal izquierda

        g2d.drawLine(panelWidth - 100,centro_y,panelWidth - 100,panelHeight); //  Linea vertical derecha
        g2d.drawLine(100,(int) (panelHeight*0.25),100,panelHeight); //  Linea vertical izquierda

        g2d.drawLine(centro_x,y1,centro_x+10,y1-10);    //  Flecha
        g2d.drawLine(centro_x,y1,centro_x-10,y1-10);    //  de flujo

        // fuente con el tamaño especificado
        g2d.setFont(textoFont);

        // Dibuja el texto centrado
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(texto)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(texto, x, y);
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

    public void manejoSalidas(String _texto){
        String nuevoTxt = quitarEspacios(_texto);
        asignarVariable(nuevoTxt);

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
        dibujoWhile.setEntrada(this.texto);
        dibujoWhile.setTexto(this.texto);
        this.setTipo("While"+indice1);
        this.variables.add("While"+indice1);
        this.variables.add(this.texto);
    }
}
