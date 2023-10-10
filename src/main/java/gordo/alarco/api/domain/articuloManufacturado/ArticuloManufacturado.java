package gordo.alarco.api.domain.articuloManufacturado;

import gordo.alarco.api.domain.articuloManufacturadoDetalle.ArticuloManufacturadoDetalle;
import gordo.alarco.api.domain.detalleFactura.DetalleFactura;
import gordo.alarco.api.domain.detallePedido.DetallePedido;
import gordo.alarco.api.domain.rubroGeneral.RubroGeneral;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name="articulomanufacturado")
@Entity(name="ArticuloManufacturado")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ArticuloManufacturado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int tiempoestimadococina;
    private String denominacion;
    private double precioventa;
    private String imagen;
    private Boolean activo;
    private LocalDate fechabaja;

    @ManyToOne
    @JoinColumn(name = "rubrogeneral_id")
    private RubroGeneral rubrogeneral;

    @OneToMany(mappedBy = "articulomanufacturado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallePedidoList = new ArrayList<>();

    @OneToMany(mappedBy = "articulomanufacturado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalleFacturaList = new ArrayList<>();

    @OneToMany(mappedBy = "articulomanufacturado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticuloManufacturadoDetalle> articuloManufacturadoDetalleList = new ArrayList<>();


    public ArticuloManufacturado(DataRegisterArticuloManufacturado dataRegisterArticuloManufacturado){

        this.tiempoestimadococina = dataRegisterArticuloManufacturado.tiempoEstimadoCocina();
        this.denominacion = dataRegisterArticuloManufacturado.denominacion();
        this.precioventa = dataRegisterArticuloManufacturado.precioVenta();
        this.imagen = dataRegisterArticuloManufacturado.imagen();
        this.activo = true;

    }

    public void updateData(DataUpdateAriculoManufacturado dataUpdateAriculoManufacturado) {

        this.tiempoestimadococina = dataUpdateAriculoManufacturado.tiempoEstimadoCocina();
        this.denominacion = dataUpdateAriculoManufacturado.denominacion();
        this.precioventa = dataUpdateAriculoManufacturado.precioVenta();
        this.imagen = dataUpdateAriculoManufacturado.imagen();

    }

    public void deleteArticuloManufacturado() {

        this.activo = false;
        this.fechabaja = LocalDate.now();

    }
}
