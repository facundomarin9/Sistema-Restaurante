package gordo.alarco.api.domain.articuloManufacturado;

import gordo.alarco.api.domain.articuloInsumo.ArticuloInsumo;
import gordo.alarco.api.domain.articuloManufacturadoDetalle.ArticuloManufacturadoDetalle;
import gordo.alarco.api.domain.articuloManufacturadoDetalle.DataListArticuloManufacturadoDetalle;

import java.util.List;

public record DataListArticuloManufacturado(
        Long id,
        int tiempoEstimadoCocina,
        String denominacion,
        double precioVenta,
        String imagen



) {

    public DataListArticuloManufacturado(ArticuloManufacturado articuloManufacturado){

        this(articuloManufacturado.getId(), articuloManufacturado.getTiempoestimadococina(), articuloManufacturado.getDenominacion(),
                articuloManufacturado.getPrecioventa(), articuloManufacturado.getImagen());

    }


}
