package gordo.alarco.api.domain.configuracion;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name="configuracion")
@Entity(name="Configuration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidadcocineros;
    private String emailempresa;
    private String tokenmercadopago;
    private Boolean activo;
    private LocalDate fechabaja;

    public Configuration(DataRegisterConfiguration dataRegisterConfiguration){

        this.cantidadcocineros = dataRegisterConfiguration.cantidadCocineros();
        this.tokenmercadopago = dataRegisterConfiguration.tokenMercadoPago();
        this.emailempresa = dataRegisterConfiguration.emailEmpresa();
        this.activo = true;


    }

    public void updateData(DataUpdateConfiguration dataUpdateConfiguration) {

        this.cantidadcocineros = dataUpdateConfiguration.cantidadCocineros();
        this.tokenmercadopago = dataUpdateConfiguration.tokenMercadoPago();
        this.emailempresa = dataUpdateConfiguration.emailEmpresa();

    }

    public void deleteConfiguration() {

        this.activo = false;
        this.fechabaja = LocalDate.now();

    }
}
