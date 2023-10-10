package gordo.alarco.api.domain.detallePedido;

public record DataUpdateDetallePedido(
        Long id,
        int cantidad,
        double subTotal) {
}
