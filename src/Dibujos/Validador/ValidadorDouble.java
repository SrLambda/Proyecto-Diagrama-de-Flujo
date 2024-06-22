package Dibujos.Validador;

public class ValidadorDouble implements Validador {

    @Override
    public boolean validar(String entrada) {
        if(entrada == null || entrada.isEmpty()){
            return false;
        }
        try{
            Double.parseDouble(entrada);
            System.out.println("DOUBLE!");
            return true;
        }
        catch (NumberFormatException ex1){
            return false;
        }
    }
}
