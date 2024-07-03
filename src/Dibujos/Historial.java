package Dibujos;
import java.util.Stack;

public class Historial {
    private Stack<EstadoFigura> historialUndo;
    private Stack<EstadoFigura> historialRedo;

    public Historial() {
        this.historialUndo = new Stack<>();
        this.historialRedo = new Stack<>();
    }

    public void guardarEstado(EstadoFigura estado) {
        historialUndo.push(estado);
        historialRedo.clear(); // Limpiar historial de redo al guardar nuevo estado
    }

    public EstadoFigura deshacer() {
        if (!historialUndo.isEmpty()) {
            EstadoFigura estadoActual = historialUndo.pop();
            historialRedo.push(estadoActual);
            return estadoActual;
        }
        return null; // No hay más estados para deshacer
    }

    public EstadoFigura rehacer() {
        if (!historialRedo.isEmpty()) {
            EstadoFigura estadoActual = historialRedo.pop();
            historialUndo.push(estadoActual);
            return estadoActual;
        }
        return null; // No hay más estados para rehacer
    }
}
