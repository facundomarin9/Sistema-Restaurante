package gordo.alarco.api.domain.configuracion;

public record DataUpdateConfiguration(
        Long id,
        int cantidadCocineros,
        String emailEmpresa,
        String tokenMercadoPago ) {
}
