package gordo.alarco.api.domain.articuloManufacturadoDetalle;

import gordo.alarco.api.domain.articuloInsumo.ArticuloInsumo;

public record DataResponseArticuloManufacturadoDetalles(
        double cantidad,
        String unidadMedida,
        String denominacion
) {
}
