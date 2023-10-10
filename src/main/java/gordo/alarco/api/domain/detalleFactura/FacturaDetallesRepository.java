package gordo.alarco.api.domain.detalleFactura;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDetallesRepository extends JpaRepository<DetalleFactura, Long> {
    Page<DetalleFactura> findAllByFactura_id(Long id, Pageable paginacion);
}
