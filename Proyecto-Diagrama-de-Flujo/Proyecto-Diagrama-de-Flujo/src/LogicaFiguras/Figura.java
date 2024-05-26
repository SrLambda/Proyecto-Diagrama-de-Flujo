package LogicaFiguras;

public abstract class  Figura
{

    protected String texto;

    protected Figura siguiente;


    abstract public String getCodigo();


    abstract public void setTexto(String _texto);


    abstract public String getTexto();


}
