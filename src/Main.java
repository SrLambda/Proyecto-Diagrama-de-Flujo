public class Main {
    public static void main(String[] args) {
        Controlador controlador = Controlador.getInstancia();
        new Front(controlador);
    }
}