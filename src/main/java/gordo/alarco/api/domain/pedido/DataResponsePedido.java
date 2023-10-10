package gordo.alarco.api.domain.pedido;

import gordo.alarco.api.domain.client.Cliente;

import java.time.LocalTime;

public record DataResponsePedido(

        Long numero,
        Estado estado,
        TipoEnvio tipoEnvio,
        LocalTime horaEstimadaFin,
        double total,
        String nombre,
        String apellido,
        String calle,
        long numeroCalle,
        String localidad




) {
}
