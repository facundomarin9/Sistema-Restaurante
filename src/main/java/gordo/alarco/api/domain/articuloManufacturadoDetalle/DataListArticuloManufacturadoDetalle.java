package gordo.alarco.api.domain.articuloManufacturadoDetalle;

import gordo.alarco.api.domain.articuloInsumo.ArticuloInsumo;
import gordo.alarco.api.domain.articuloInsumo.DataListArticuloInsumo;

public record DataListArticuloManufacturadoDetalle(

        double cantidad,
        String unidadMedida,
        String denominacion

) {
}
