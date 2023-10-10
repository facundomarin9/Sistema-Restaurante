package gordo.alarco.api.domain.articuloInsumo;

import gordo.alarco.api.domain.rubroArticulo.RubroArticulo;

public record DataRegisterArticuloInsumo(
        String denominacion,
        double precioCompra,
        double precioVenta,
        double stockActual,
        double stockMinimo,
        String unidadMedida,
        boolean esInsumo,
        RubroArticulo rubroArticulo
) {
}
