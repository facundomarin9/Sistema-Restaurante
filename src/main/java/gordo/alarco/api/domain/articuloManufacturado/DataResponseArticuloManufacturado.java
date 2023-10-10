package gordo.alarco.api.domain.articuloManufacturado;

import gordo.alarco.api.domain.articuloManufacturadoDetalle.ArticuloManufacturadoDetalle;
import gordo.alarco.api.domain.articuloManufacturadoDetalle.DataResponseArticuloManufacturadoDetalles;

import java.util.List;

public record DataResponseArticuloManufacturado(

        int tiempoEstimadoCocina,
        String denominacion,
        double precioVenta,
        String imagen,
        List<DataResponseArticuloManufacturadoDetalles> articuloManufacturadoDetalles


) {
}
