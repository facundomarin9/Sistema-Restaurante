package gordo.alarco.api.domain.address;

import gordo.alarco.api.domain.client.Cliente;
import gordo.alarco.api.domain.pedido.Pedido;
import gordo.alarco.api.domain.user.DataAddress;
import jakarta.persistence.*;
import lombok.*;

@Table(name="domicilio")
@Entity(name="Domicilio")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Domicilio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private long numero;
    private String localidad;
    @OneToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;




    public Domicilio(DataAddress domicilio, Cliente cliente) {

        this.calle = domicilio.calle();
        this.numero = domicilio.numero();
        this.localidad = domicilio.localidad();
        this.cliente = cliente;

    }

    public Domicilio updateData(DataAddress domicilio) {

        if(domicilio.calle() != null){
            this.calle = domicilio.calle();
        }
        if(domicilio.numero() != 0){
            this.numero = domicilio.numero();
        }
        if(domicilio.localidad() != null){
            this.localidad = domicilio.localidad();
        }
        return this;
    }
}
