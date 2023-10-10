package gordo.alarco.api.domain.mercadoPagoDatos;

import gordo.alarco.api.domain.pedido.DataUpdatePedido;

public record DataUpdateMPDatos(

        Long id,
        Long identificadorPago,
        String formaPago,
        String metodoPago,
        String numeroTarjeta,
        String estado,
        DataUpdatePedido pedido

) {
}
