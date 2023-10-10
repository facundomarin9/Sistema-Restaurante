package gordo.alarco.api.domain.user;

import gordo.alarco.api.domain.client.Cliente;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name="users")
@Entity(name="Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String clave;
    private Boolean activo;
    private LocalDate fechabaja;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @Embedded
    private Cliente cliente;


    public User(DataRegisterUser dataRegisterUser) {

        this.usuario = dataRegisterUser.usuario();
        this.clave = dataRegisterUser.clave();
        this.role = dataRegisterUser.role();
        this.activo = true;

    }

    public void updateData(DataUpdateUser dataUpdateUser) {
        if(dataUpdateUser.clave() != null){

            this.clave = dataUpdateUser.clave();
        }
        if(dataUpdateUser.cliente() != null){

            this.cliente = cliente.updateData(dataUpdateUser.cliente());

        }



    }

    public void deleteUser() {

        this.activo = false;
        this.fechabaja = LocalDate.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
