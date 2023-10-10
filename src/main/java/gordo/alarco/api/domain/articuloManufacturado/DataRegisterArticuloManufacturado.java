package gordo.alarco.api.domain.articuloManufacturado;

import java.util.List;

public record DataRegisterArticuloManufacturado(
        int tiempoEstimadoCocina,
        String denominacion,
        double precioVenta,
        String imagen,
        Long rubroGeneralId,
        List<DataRegisterArticuloManufacturadoDetalle> articuloManufacturadoDetalle


) {
}
