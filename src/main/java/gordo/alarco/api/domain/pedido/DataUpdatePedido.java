package gordo.alarco.api.domain.pedido;

import gordo.alarco.api.domain.detallePedido.DataUpdateDetallePedido;
import gordo.alarco.api.domain.detallePedido.DetallePedido;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

public record DataUpdatePedido(
        @NotNull
        Long id,
        Estado estado,
        LocalTime horaEstimadaFin,
        TipoEnvio tipoEnvio,
        double total,
        List<DataUpdateDetallePedido> detallePedido

) {
}
