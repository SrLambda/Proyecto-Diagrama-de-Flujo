package Mapa;

import java.util.HashMap;

public class Mapa {
    java.util.Map<Object,Object> mapa = new HashMap<>();

    public String obtenerVariable(String _cadena){
        String _variable = "";
        int i = 0;
        while(i < _cadena.length() && _cadena.charAt(i) != ' '){
            _variable = _variable +_cadena.charAt(i);
            i++;
        }
        return _variable;
    }

    public String obtenerClave(String _cadena){
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

    public <T,K> void agregarVarYKey(T _variable,K _clave){
        mapa.put(_variable,_clave);
        System.out.println("Agregado:"+_variable+"/"+_clave);
        System.out.println(":"+mapa);
    }
}
