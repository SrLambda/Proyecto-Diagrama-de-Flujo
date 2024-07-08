package Dibujos.Validador;

public class ValidadorCadena implements Validador{

    @Override
    public boolean validar(String entrada) {
        if(entrada == null || entrada.isEmpty()){
            return false;
        }
        else{
            for(char caracter : entrada.toCharArray()){
                if (!Character.isLetter(caracter)) {
                    return false;
                }
            }
            return true;
        }
    }
}
