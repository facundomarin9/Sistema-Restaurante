package gordo.alarco.api.domain.articuloManufacturadoDetalle;

import gordo.alarco.api.domain.articuloInsumo.ArticuloInsumo;

public record DataUpdateDetalleAM(

        Long id,
        double cantidad,
        String unidadMedida,
        ArticuloInsumo articuloInsumo

) {
}
