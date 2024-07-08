package Dibujos.Validador;

public class ValidadorEntero implements Validador {

    @Override
    public boolean validar(String entrada) {
        if(entrada == null || entrada.isEmpty()){
            return false;
        }
        try{
            Integer.parseInt(entrada);
            return true;
        }
        catch (NumberFormatException ex1){
            return false;
        }
    }
}
