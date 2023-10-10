package gordo.alarco.api.domain.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Page<Pedido> findByEstado(Estado estado, Pageable paginacion);
    Pedido findFirstByOrderByIdDesc();


}
