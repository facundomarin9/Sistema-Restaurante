package gordo.alarco.api.domain.mercadoPagoDatos;

import gordo.alarco.api.domain.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name="mercadopagodatos")
@Entity(name="MercadoPagoDatos")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MercadoPagoDatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long identificadorpago;
    private LocalDate fechacreacion;
    private LocalDate fechaaprobacion;
    private String formapago;
    private String metodopago;
    private String numerotarjeta;
    private String estado;
    private Boolean activo;
    private LocalDate fechabaja;

    @OneToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;

    public MercadoPagoDatos(DataRegisterMPDatos dataRegisterMPDatos) {

        this.identificadorpago = dataRegisterMPDatos.identificadorPago();
        this.fechacreacion = LocalDate.now();
        this.fechaaprobacion = LocalDate.now();
        this.formapago = dataRegisterMPDatos.formaPago();
        this.metodopago = dataRegisterMPDatos.metodoPago();
        this.numerotarjeta = dataRegisterMPDatos.numeroTarjeta();
        this.estado = dataRegisterMPDatos.estado();
        this.activo = true;

    }

    public void updateData(DataUpdateMPDatos dataUpdateMPDatos) {

        this.identificadorpago = dataUpdateMPDatos.identificadorPago();
        this.formapago = dataUpdateMPDatos.formaPago();
        this.metodopago = dataUpdateMPDatos.metodoPago();
        this.numerotarjeta = dataUpdateMPDatos.numeroTarjeta();
        this.estado = dataUpdateMPDatos.estado();

    }

    public void deleteMercadoPagoDatos() {

        this.activo = false;
        this.fechabaja = LocalDate.now();

    }
}
