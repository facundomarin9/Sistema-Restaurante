package gordo.alarco.api.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {


     Page<User> findByActivoTrue(Pageable paginacion);

     UserDetails findByUsuario(String username);
}
