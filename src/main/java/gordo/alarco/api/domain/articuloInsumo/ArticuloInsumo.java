package gordo.alarco.api.domain.articuloInsumo;

import gordo.alarco.api.domain.rubroArticulo.RubroArticulo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name="articuloinsumo")
@Entity(name="ArticuloInsumo")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ArticuloInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denominacion;
    private double preciocompra;
    private double precioventa;
    private double stockactual;
    private double stockminimo;
    private String unidadmedida;
    private boolean esinsumo;
    private Boolean activo;
    private LocalDate fechabaja;

    @ManyToOne
    @JoinColumn(name = "rubroarticulo_id")
    private RubroArticulo rubroarticulo;

    public ArticuloInsumo(DataRegisterArticuloInsumo dataRegisterArticuloInsumo) {

        this.denominacion = dataRegisterArticuloInsumo.denominacion();
        this.preciocompra = dataRegisterArticuloInsumo.precioCompra();
        this.precioventa = dataRegisterArticuloInsumo.precioVenta();
        this.stockactual = dataRegisterArticuloInsumo.stockActual();
        this.stockminimo = dataRegisterArticuloInsumo.stockMinimo();
        this.unidadmedida = dataRegisterArticuloInsumo.unidadMedida();
        this.esinsumo = dataRegisterArticuloInsumo.esInsumo();
        this.activo = true;

    }

    public void updateData(DataUpdateArticuloInsumo dataUpdateArticuloInsumo) {

        this.denominacion = dataUpdateArticuloInsumo.denominacion();
        this.preciocompra = dataUpdateArticuloInsumo.precioCompra();
        this.precioventa = dataUpdateArticuloInsumo.precioVenta();
        this.stockactual = dataUpdateArticuloInsumo.stockActual();
        this.stockminimo = dataUpdateArticuloInsumo.stockMinimo();
        this.unidadmedida = dataUpdateArticuloInsumo.unidadMedida();
        this.esinsumo = dataUpdateArticuloInsumo.esInsumo();


    }

    public void deleteArticuloInsumo() {

        this.activo = false;
        this.fechabaja = LocalDate.now();

    }
}
