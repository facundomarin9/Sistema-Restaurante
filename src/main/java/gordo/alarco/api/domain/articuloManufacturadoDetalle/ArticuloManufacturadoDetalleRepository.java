package gordo.alarco.api.domain.articuloManufacturadoDetalle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticuloManufacturadoDetalleRepository extends JpaRepository<ArticuloManufacturadoDetalle, Long> {
    List<ArticuloManufacturadoDetalle> findAllByArticulomanufacturado_id(Long id);
}
