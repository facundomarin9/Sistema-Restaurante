package gordo.alarco.api.domain.detallePedido;


import gordo.alarco.api.domain.articuloManufacturado.ArticuloManufacturado;
import gordo.alarco.api.domain.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name="detallepedido")
@Entity(name="DetallePedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private double subtotal;
    private Boolean activo;
    private LocalDate fechabaja;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "articulomanufacturado_id")
    private ArticuloManufacturado articulomanufacturado;


    public DetallePedido (DataRegisterDetallePedido detallePedido){

        this.cantidad = detallePedido.cantidad();
        this.subtotal = detallePedido.subTotal();
        this.activo = true;

    }

    public void updateData(DataUpdateDetallePedido detallePedido){
        this.cantidad = detallePedido.cantidad();
        this.subtotal = detallePedido.subTotal();
    }

    public void deleteDetallePedidos() {

        this.activo = false;
        this.fechabaja = LocalDate.now();


    }


    //seguir agregando

}
