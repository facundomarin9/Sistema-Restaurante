package gordo.alarco.api.domain.articuloManufacturado;

import gordo.alarco.api.domain.articuloManufacturadoDetalle.DataUpdateDetalleAM;

import java.util.List;

public record DataUpdateAriculoManufacturado(

        Long id,
        int tiempoEstimadoCocina,
        String denominacion,
        double precioVenta,
        String imagen,
        List<DataUpdateDetalleAM> detallesAMList

) {
}
