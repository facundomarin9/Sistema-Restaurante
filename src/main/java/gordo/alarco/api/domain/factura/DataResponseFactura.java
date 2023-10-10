package gordo.alarco.api.domain.factura;

public record DataResponseFactura(

        Long numero,
        String formaDePago,
        String numeroTarjeta,
        double totalVenta,
        Long numeroPedido

) {
}
