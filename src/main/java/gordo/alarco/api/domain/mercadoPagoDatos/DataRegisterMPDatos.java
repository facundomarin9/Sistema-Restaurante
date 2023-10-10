package gordo.alarco.api.domain.mercadoPagoDatos;

import java.time.LocalDate;

public record DataRegisterMPDatos(

        Long identificadorPago,
        String formaPago,
        String metodoPago,
        String numeroTarjeta,
        String estado,
        Long pedidoId

) {
}
