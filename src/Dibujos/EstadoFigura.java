package Dibujos;
import java.awt.*;

public class EstadoFigura {
    private Point posicion;
    private Dimension tamaño;
    private String texto;

    public EstadoFigura(Point posicion, Dimension tamaño, String texto) {
        this.posicion = posicion;
        this.tamaño = tamaño;
        this.texto = texto;
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }

    public Dimension getTamaño() {
        return tamaño;
    }

    public void setTamaño(Dimension tamaño) {
        this.tamaño = tamaño;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}