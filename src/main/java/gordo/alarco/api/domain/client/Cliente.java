package gordo.alarco.api.domain.client;

import gordo.alarco.api.domain.address.Domicilio;
import gordo.alarco.api.domain.pedido.Pedido;
import gordo.alarco.api.domain.user.DataClient;
import gordo.alarco.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name="clientes")
@Entity(name="Cliente")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Long telefono;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente")
    @Embedded
    private Domicilio domicilio;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
///////////////////////////////////
    @OneToMany(mappedBy="cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(DataClient cliente, User user) {

        this.nombre = cliente.nombre();
        this.apellido = cliente.apellido();
        this.telefono = cliente.telefono();
        this.email = cliente.email();
        this.user = user;


    }

    public Cliente updateData(DataClient cliente) {

        if(cliente.nombre() != null){
            this.nombre = cliente.nombre();
        }
        if(cliente.apellido() != null){
            this.apellido = cliente.apellido();
        }
        if(cliente.telefono() != null){
            this.telefono = cliente.telefono();
        }
        if(cliente.email() != null){

            this.email = cliente.email();

        }

        if(cliente.domicilio() != null){
            this.domicilio = domicilio.updateData(cliente.domicilio());
        }

        return this;

    }
}
