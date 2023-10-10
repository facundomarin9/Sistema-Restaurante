package gordo.alarco.api.domain.factura;

import gordo.alarco.api.domain.detalleFactura.DetalleFactura;
import gordo.alarco.api.domain.detallePedido.DetallePedido;
import gordo.alarco.api.domain.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name="factura")
@Entity(name="Factura")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha = LocalDate.now();;
    private Long numero;
    private double montodescuento;
    private String formapago;
    private String numerotarjeta;
    private double totalventa;
    private double totalcosto;

    private Boolean activo;
    private LocalDate fechabaja;

    @OneToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();

    public Factura(DataRegisterFactura dataRegisterFactura, Pedido pedido){

        this.numero = 0L;
        this.montodescuento = dataRegisterFactura.montoDescuento();
        this.formapago = dataRegisterFactura.formaDePago();
        this.numerotarjeta = dataRegisterFactura.numeroTarjeta();
        this.totalventa = dataRegisterFactura.totalVenta();
        this.totalcosto = dataRegisterFactura.totalCosto();
        this.pedido = pedido;
        this.activo = true;




    }


    public void deleteFactura() {

        this.activo = false;
        this.fechabaja = LocalDate.now();

    }

    public void updateData(DataUpdateFactura dataUpdateFactura) {

        this.montodescuento = dataUpdateFactura.montoDescuento();
        this.formapago = dataUpdateFactura.formaDePago();
        this.numerotarjeta = dataUpdateFactura.numeroTarjeta();
        this.totalventa = dataUpdateFactura.totalVenta();
        this.totalcosto = dataUpdateFactura.totalCosto();


    }
}
