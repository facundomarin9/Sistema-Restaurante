package gordo.alarco.api.domain.rubroArticulo;

public record DataListRubroArticulo(

        Long id,
        String denominacion

) {

    public DataListRubroArticulo (RubroArticulo rubroArticulo){

        this(rubroArticulo.getId(), rubroArticulo.getDenominacion());

    }

}
