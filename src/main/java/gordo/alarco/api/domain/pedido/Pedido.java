package gordo.alarco.api.domain.pedido;
import gordo.alarco.api.domain.address.Domicilio;
import gordo.alarco.api.domain.client.Cliente;
import gordo.alarco.api.domain.detallePedido.DetallePedido;
import gordo.alarco.api.domain.factura.Factura;

import gordo.alarco.api.domain.mercadoPagoDatos.MercadoPagoDatos;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Table(name="pedido")
@Entity(name="Pedido")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha = LocalDate.now();
    private Long numero;
    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.A_CONFIRMAR;
    private LocalTime horaestimadafin;
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoenvio;
    private double total;
    private Boolean activo;
    private LocalDate fechabaja;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    @Embedded
    private Factura factura;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    @Embedded
    private MercadoPagoDatos mercadopagodatos;

    public Pedido(DataRegisterPedido dataRegisterPedido, Cliente cliente){


        this.numero = this.getId();
        this.horaestimadafin = dataRegisterPedido.horaEstimadaFin();
        this.tipoenvio = dataRegisterPedido.tipoEnvio();
        this.total = dataRegisterPedido.total();
        this.domicilio = cliente.getDomicilio();
        this.cliente = cliente;
        this.activo = true;


    }

    public void updateData(DataUpdatePedido dataUpdatePedido){

        this.estado = dataUpdatePedido.estado();
        this.horaestimadafin = dataUpdatePedido.horaEstimadaFin();
        this.tipoenvio = dataUpdatePedido.tipoEnvio();
        this.total = dataUpdatePedido.total();








    }

    public void deletePedido() {

        this.activo = false;
        this.fechabaja = LocalDate.now();



    }
}
