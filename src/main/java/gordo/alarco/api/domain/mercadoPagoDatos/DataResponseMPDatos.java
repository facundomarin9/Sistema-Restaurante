package gordo.alarco.api.domain.mercadoPagoDatos;

import java.time.LocalDate;

public record DataResponseMPDatos(

        LocalDate fechaCreacion,
        LocalDate fechaAprobacion,
        String formaPago,
        String metodoPago,
        String numeroTarjeta,
        String estado,
        Long pedidoId

) {
}
