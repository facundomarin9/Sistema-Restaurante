package gordo.alarco.api.domain.factura;

import gordo.alarco.api.domain.detalleFactura.DetalleFactura;
import gordo.alarco.api.domain.pedido.Pedido;

import java.util.List;

public record DataListFactura(

        Long numero,
        String formaDePago,
        String numeroTarjeta,
        double totalVenta,
        Long numeroPedido



) {

    public DataListFactura(Factura factura){

        this(factura.getNumero(), factura.getFormapago(), factura.getNumerotarjeta(), factura.getTotalventa(),
                factura.getPedido().getNumero());

    }



}
