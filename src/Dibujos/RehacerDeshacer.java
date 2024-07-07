package Dibujos;

import java.util.List;
import java.util.Stack;

public class RehacerDeshacer {
    private static RehacerDeshacer instance;
    private List<PanelPersonalizado> figuras;
    private Stack<Runnable> undoStack = new Stack<>();
    private Stack<Runnable> redoStack = new Stack<>();

    private RehacerDeshacer(List<PanelPersonalizado> figuras) {
        this.figuras = figuras;
    }

    public static RehacerDeshacer getInstance(List<PanelPersonalizado> figuras) {
        if (instance == null) {
            instance = new RehacerDeshacer(figuras);
        }
        return instance;
    }


    public void agregar(PanelPersonalizado figura) {
        undoStack.push(() -> {
            figura.eliminarFigura();
            System.out.println("Figura removida: " + figura);
        });
        System.out.println("Figura agregada: " + figura);
        redoStack.clear();
    }


    public void remover(PanelPersonalizado figura) {
        undoStack.push(() -> {
            figuras.add(figura);
            System.out.println("Figura reagregada: " + figura);
        });
        System.out.println("Figura removida: " + figura);
        redoStack.clear();
    }


    public void deshacer() {
        if (!undoStack.isEmpty()) {
            Runnable action = undoStack.pop();
            redoStack.push(action);
            action.run();
        } else {
            System.out.println("No hay acciones para deshacer.");
        }
    }


    public void rehacer() {
        if (!redoStack.isEmpty()) {
            Runnable action = redoStack.pop();
            undoStack.push(action);
            action.run();
        } else {
            System.out.println("No hay acciones para rehacer.");
        }
    }



    public void mostrarFiguras() {
        for (int i=0; i<figuras.size(); i++){
            System.out.println(figuras.get(i).getTexto());
        }
    }

}

/*
───────────────────────
───────  ─██████████████─
───────  ─██░░░░░░░░░░██─
─██████─  ██░░██████░░██─
─██░░██  ─██░░██──██░░██─
─██████─  ██░░██──██░░██─
────────  ██░░██──██░░██─
─██████─  ██░░██──██░░██─
─██░░██─  ██░░██──██░░██─
─██████─  ██░░██████░░██─
────────  ██░░░░░░░░░░██─
────────  ██████████████─
───────────────────────
 */
