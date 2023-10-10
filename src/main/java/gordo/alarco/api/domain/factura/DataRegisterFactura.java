package gordo.alarco.api.domain.factura;

import gordo.alarco.api.domain.detalleFactura.DetalleFactura;

import java.util.List;

public record DataRegisterFactura(
                                  double montoDescuento,
                                  String formaDePago,
                                  String numeroTarjeta,
                                  double totalVenta,
                                  double totalCosto,
                                  Long pedidoId,
                                  List<DataRegisterDetalleFactura> detalleFactura

                                  ) {
}
