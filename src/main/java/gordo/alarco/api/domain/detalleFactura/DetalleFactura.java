package gordo.alarco.api.domain.detalleFactura;


import gordo.alarco.api.domain.articuloManufacturado.ArticuloManufacturado;
import gordo.alarco.api.domain.factura.DataRegisterDetalleFactura;
import gordo.alarco.api.domain.factura.Factura;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name="detallefactura")
@Entity(name="DetalleFactura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private double subtotal;
    private Boolean activo;
    private LocalDate fechabaja;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "articulomanufacturado_id")
    private ArticuloManufacturado articulomanufacturado;

    public DetalleFactura (DataRegisterDetalleFactura dataRegisterDetalleFactura){

        this.cantidad = dataRegisterDetalleFactura.cantidad();
        this.subtotal = dataRegisterDetalleFactura.subTotal();
        this.activo = true;


    }


    public void updateData(DataUpdateDetalleFactura as) {

        this.cantidad = as.cantidad();
        this.subtotal = as.subTotal();

    }
}
