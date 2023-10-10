package gordo.alarco.api.domain.detalleFactura;

public record DataUpdateDetalleFactura(
        Long id,
        int cantidad,
        double subTotal
) {
}
