package gordo.alarco.api.domain.rubroGeneral;

public record DataListRubroGeneral(Long id, String denominacion) {


    public DataListRubroGeneral(RubroGeneral rubroGeneral){

        this(rubroGeneral.getId(), rubroGeneral.getDenominacion());

    }


}


