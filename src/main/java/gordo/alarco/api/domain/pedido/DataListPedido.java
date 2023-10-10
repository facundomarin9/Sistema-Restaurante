package gordo.alarco.api.domain.pedido;

import java.time.LocalTime;

public record DataListPedido(Long id, Estado estado, LocalTime horaEstimadaFin, TipoEnvio tipoEnvio, double total) {

    public DataListPedido(Pedido pedido){

        this(pedido.getId(), pedido.getEstado(), pedido.getHoraestimadafin(), pedido.getTipoenvio(), pedido.getTotal());

    }

}
