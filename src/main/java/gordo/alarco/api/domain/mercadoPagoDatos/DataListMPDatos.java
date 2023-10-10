package gordo.alarco.api.domain.mercadoPagoDatos;

import gordo.alarco.api.domain.pedido.Pedido;

import java.time.LocalDate;

public record DataListMPDatos(
        Long id,
        LocalDate fechaCreacion,
        LocalDate fechaAprobacion,
        String formaPago,
        String metodoPago,
        String numeroTarjeta,
        String estado,
        Long pedidoId
) {

    public DataListMPDatos(MercadoPagoDatos mercadoPagoDatos){
        this(mercadoPagoDatos.getId(), mercadoPagoDatos.getFechacreacion(), mercadoPagoDatos.getFechaaprobacion(), mercadoPagoDatos.getFormapago(),
                mercadoPagoDatos.getMetodopago(), mercadoPagoDatos.getNumerotarjeta(), mercadoPagoDatos.getEstado(), mercadoPagoDatos.getPedido().getId());
    }

}
