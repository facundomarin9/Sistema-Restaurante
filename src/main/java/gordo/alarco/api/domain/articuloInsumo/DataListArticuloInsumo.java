package gordo.alarco.api.domain.articuloInsumo;

import gordo.alarco.api.domain.rubroArticulo.RubroArticulo;

public record DataListArticuloInsumo(
        Long id,
        String denominacion,
        Double precioCompra,
        Double precioVenta,
        Double stockActual,
        Double stockMinimo,
        String unidadMedida,
        Boolean esInsumo

                            ) {

    public DataListArticuloInsumo(ArticuloInsumo articuloInsumo){

        this(articuloInsumo.getId(), articuloInsumo.getDenominacion(),articuloInsumo.getPreciocompra(),
                articuloInsumo.getPrecioventa(), articuloInsumo.getStockactual(), articuloInsumo.getStockminimo(),
                articuloInsumo.getUnidadmedida(), articuloInsumo.isEsinsumo());

    }


}
