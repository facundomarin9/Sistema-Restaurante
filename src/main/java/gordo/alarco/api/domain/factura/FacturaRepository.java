package gordo.alarco.api.domain.factura;
import gordo.alarco.api.domain.client.Cliente;
import gordo.alarco.api.domain.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

    Factura findFirstByOrderByIdDesc();

}
