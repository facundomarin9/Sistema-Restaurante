package gordo.alarco.api.domain.factura;

import gordo.alarco.api.domain.detalleFactura.DataUpdateDetalleFactura;
import gordo.alarco.api.domain.detalleFactura.DetalleFactura;

import java.util.List;

public record DataUpdateFactura(

        Long id,
        double montoDescuento,
        String formaDePago,
        String numeroTarjeta,
        double totalVenta,
        double totalCosto,
        Long pedidoId,
        List<DataUpdateDetalleFactura> detalleFactura

) {
}
