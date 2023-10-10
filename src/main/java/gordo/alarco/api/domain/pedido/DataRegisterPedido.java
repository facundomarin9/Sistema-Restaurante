package gordo.alarco.api.domain.pedido;

import gordo.alarco.api.domain.client.Cliente;
import gordo.alarco.api.domain.detallePedido.DataRegisterDetallePedido;
import gordo.alarco.api.domain.factura.DataRegisterFactura;
import gordo.alarco.api.domain.factura.Factura;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DataRegisterPedido(


        LocalTime horaEstimadaFin,
        TipoEnvio tipoEnvio,
        double total,
        List<DataRegisterDetallePedido> detallePedido


) {
}
