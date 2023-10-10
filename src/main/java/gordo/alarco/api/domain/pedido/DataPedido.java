package gordo.alarco.api.domain.pedido;

import gordo.alarco.api.domain.client.Cliente;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataPedido(

    int numero,
    LocalTime horaEstimadaFin,
    TipoEnvio tipoEnvio,
    Double total,
    Cliente cliente




) {
}
