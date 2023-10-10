package gordo.alarco.api.domain.articuloInsumo;

import gordo.alarco.api.domain.rubroArticulo.RubroArticulo;

public record DataResponseArticulo(

        Long id,
        String denominacion,
        Double precioCompra,
        Double precioVenta,
        Double stockActual,
        Double stockMinimo,
        String unidadMedida,
        Boolean esInsumo,
        String rubroDenominacion

) {
}
