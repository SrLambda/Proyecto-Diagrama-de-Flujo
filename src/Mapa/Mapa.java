import java.util.HashMap;

public class Mapa {
    java.util.Map<Object,Object> mapa = new HashMap<>();

    public <K,V> void agregarElementos(K _clave, V _valor){
        System.out.println("Agregando elemento con clave ["+_clave+"] y valor ["+_valor+"]");
        mapa.put(_clave,_valor);
    }



}
