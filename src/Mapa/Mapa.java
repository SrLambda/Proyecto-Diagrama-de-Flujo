package Mapa;

import java.util.HashMap;

public class Mapa {
    java.util.Map<Object,Object> mapa = new HashMap<>();

    public String obtenerClave(String _cadena){
        String _variable = "";
        int i = 0;
        while(i < _cadena.length() && _cadena.charAt(i) != ' '){
            _variable = _variable +_cadena.charAt(i);
            i++;
        }
        return _variable;
    }

    public String obtenerValor(String _cadena){
        int i = 0;
        int contarEspacios = 0;
        boolean leyendoClave = false;
        String _clave = "";
        while(i < _cadena.length()){
            if(_cadena.charAt(i) == ' '){
                contarEspacios++;
                if(contarEspacios == 2){
                    leyendoClave = true;
                }
            }else if(leyendoClave){
                _clave = _clave + _cadena.charAt(i);
            }
            i++;
        }
        return _clave;
    }

    public <T,K> void agregarClaveValor(T _clave,K _valor){
        mapa.put(_clave,_valor);
        System.out.println("Agregado:"+_clave+"/"+_valor);
        System.out.println(":"+mapa);
    }

    public <T,K> void cambiarValor(T _clave, K _valor){
        mapa.put(_clave,_valor);
        System.out.println("Editado:"+_clave+"/"+_valor);
        System.out.println(":"+mapa);
    }
}
