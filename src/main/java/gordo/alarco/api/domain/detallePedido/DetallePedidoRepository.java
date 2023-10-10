package gordo.alarco.api.domain.detallePedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findAllByPedido_id(Long id);
}
