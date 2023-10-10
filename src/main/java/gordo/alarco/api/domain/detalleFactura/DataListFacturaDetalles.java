package gordo.alarco.api.domain.detalleFactura;

import gordo.alarco.api.domain.detallePedido.DetallePedido;

public record DataListFacturaDetalles(
        int cantidad,
        double subTotal
) {

    public DataListFacturaDetalles(DetalleFactura detalleFactura){

        this(detalleFactura.getCantidad(), detalleFactura.getSubtotal());

    }

}
