package LogicaFiguras;

public class InicioFin extends Figura {
    InicioFin(boolean _esInicio) {
        
        if(_esInicio)
        {
            
            this.texto = "Inicio";
            
        }
        else 
        {
            
            this.texto = "Fin";
        }
    }

    @Override
    public String getCodigo() {
        
        if(this.texto.equals("Inicio")){
            
            return "in";
            
        }
        else
        {
            
            return "fn";
            
        }
    }

    @Override
    public void setTexto(String _texto) {

        System.out.println("TEXTO INABILITADO");
        
    }
    

    @Override
    public String getTexto() 
    {
        
        return null;
        
    }
}
