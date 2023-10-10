package gordo.alarco.api.domain.configuracion;

public record DataListConfiguration(
        Long id,
        int numeroCocineros,
        String emailEmpresa,
        String tokenMercadoPago
) {

    public DataListConfiguration (Configuration configuration){

        this(configuration.getId(), configuration.getCantidadcocineros(),
                configuration.getEmailempresa(), configuration.getTokenmercadopago());

    }

}
