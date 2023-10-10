package gordo.alarco.api.domain.articuloManufacturadoDetalle;

import gordo.alarco.api.domain.articuloInsumo.ArticuloInsumo;
import gordo.alarco.api.domain.articuloManufacturado.ArticuloManufacturado;
import gordo.alarco.api.domain.articuloManufacturado.DataRegisterArticuloManufacturadoDetalle;
import jakarta.persistence.*;
import lombok.*;

@Table(name="articulomanufacturadodetalle")
@Entity(name="ArticuloManufacturadoDetalle")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ArticuloManufacturadoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double cantidad;
    private String unidadmedida;

    @ManyToOne
    @JoinColumn(name = "articulomanufacturado_id")
    private ArticuloManufacturado articulomanufacturado;
    @ManyToOne
    @JoinColumn(name = "articuloinsumo_id")
    private ArticuloInsumo articuloInsumo;

    public ArticuloManufacturadoDetalle(DataRegisterArticuloManufacturadoDetalle data) {

        this.cantidad = data.cantidad();
        this.unidadmedida = data.unidadMedida();


    }

    public void updateData(DataUpdateDetalleAM as) {

        this.cantidad = as.cantidad();
        this.unidadmedida = as.unidadMedida();


    }
}
