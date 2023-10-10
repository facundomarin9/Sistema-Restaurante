package gordo.alarco.api.domain.configuracion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    Page<Configuration> findByActivoTrue(Pageable paginacion);
}
