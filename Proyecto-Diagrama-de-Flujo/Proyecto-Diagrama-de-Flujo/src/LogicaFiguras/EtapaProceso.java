package LogicaFiguras;

public class EtapaProceso extends Figura
{

    EtapaProceso(String _texto)
    {

        this.texto = _texto;

    }

    @Override
    public String getCodigo()
    {

        return "pr;" + this.texto;

    }

    @Override
    public void setTexto(String _texto)
    {

        this.texto = _texto;

    }

    @Override
    public String getTexto()
    {

        return this.texto;

    }
}
