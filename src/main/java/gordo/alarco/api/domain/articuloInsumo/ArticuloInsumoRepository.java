package gordo.alarco.api.domain.articuloInsumo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticuloInsumoRepository extends JpaRepository<ArticuloInsumo, Long> {


    Page<ArticuloInsumo> findByEsinsumo(Boolean esInsumo, Pageable paginacion);
}
