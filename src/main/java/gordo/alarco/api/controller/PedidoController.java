package gordo.alarco.api.controller;

import gordo.alarco.api.domain.articuloManufacturado.ArticuloManufacturado;
import gordo.alarco.api.domain.articuloManufacturado.ArticuloManufacturadoRepository;
import gordo.alarco.api.domain.client.Cliente;
import gordo.alarco.api.domain.client.ClienteRepository;
import gordo.alarco.api.domain.detalleFactura.DetalleFactura;
import gordo.alarco.api.domain.detallePedido.DataRegisterDetallePedido;
import gordo.alarco.api.domain.detallePedido.DataUpdateDetallePedido;
import gordo.alarco.api.domain.detallePedido.DetallePedido;
import gordo.alarco.api.domain.detallePedido.DetallePedidoRepository;
import gordo.alarco.api.domain.factura.DataRegisterDetalleFactura;
import gordo.alarco.api.domain.factura.DataRegisterFactura;
import gordo.alarco.api.domain.factura.Factura;
import gordo.alarco.api.domain.factura.FacturaRepository;
import gordo.alarco.api.domain.mercadoPagoDatos.MercadoPagoDatos;
import gordo.alarco.api.domain.pedido.*;
import gordo.alarco.api.domain.user.DataListUser;
import gordo.alarco.api.domain.user.DataRegisterUser;
import gordo.alarco.api.domain.user.User;
import gordo.alarco.api.domain.user.UserRepository;
import gordo.alarco.api.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final UserRepository userRepository;
    private final ClienteRepository clienteRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final ArticuloManufacturadoRepository articuloManufacturadoRepository;





@Transactional
@PostMapping
public ResponseEntity<DataResponsePedido> createPedido(@RequestAttribute Long claimId, @RequestBody DataRegisterPedido dataRegisterPedido){

    User user = userRepository.getReferenceById(claimId);
    Cliente cliente = clienteRepository.getReferenceById(user.getCliente().getId());
    Pedido pedido = new Pedido(dataRegisterPedido, cliente);
    List<DetallePedido> detallePedidosList = new ArrayList<>();

    for(DataRegisterDetallePedido as : dataRegisterPedido.detallePedido().stream().toList()){


        DetallePedido detallePedido = new DetallePedido(as);
        ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getReferenceById(as.articuloManufacturadoId());
        detallePedido.setArticulomanufacturado(articuloManufacturado);
        detallePedido.setPedido(pedido);
        detallePedidosList.add(detallePedido);



    }

    pedido.setDetallePedidos(detallePedidosList);


    Pedido pedido1 = pedidoRepository.findFirstByOrderByIdDesc();

    pedido.setNumero(pedido1.getId()+1);


    pedido = pedidoRepository.save(pedido);


   DataResponsePedido dataResponsePedido = new DataResponsePedido(pedido.getNumero(),
           pedido.getEstado(), pedido.getTipoenvio(),pedido.getHoraestimadafin(), pedido.getTotal(),
           pedido.getCliente().getNombre(),pedido.getCliente().getApellido(), pedido.getCliente().getDomicilio().getCalle(),
           (int) pedido.getCliente().getDomicilio().getNumero(), pedido.getCliente().getDomicilio().getLocalidad());


    return ResponseEntity.ok(dataResponsePedido);

   }



    @Transactional
    @PostMapping("/mercadoPago")
    public ResponseEntity<DataResponsePedido> createPedidoWithMercadoPago(@RequestAttribute Long claimId, @RequestBody DataRegisterPedido dataRegisterPedido){

        User user = userRepository.getReferenceById(claimId);
        Cliente cliente = clienteRepository.getReferenceById(user.getCliente().getId());
        Pedido pedido = new Pedido(dataRegisterPedido, cliente);
        List<DetallePedido> detallePedidosList = new ArrayList<>();

        for(DataRegisterDetallePedido as : dataRegisterPedido.detallePedido().stream().toList()){


            DetallePedido detallePedido = new DetallePedido(as);
            ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getReferenceById(as.articuloManufacturadoId());
            detallePedido.setArticulomanufacturado(articuloManufacturado);
            detallePedido.setPedido(pedido);
            detallePedidosList.add(detallePedido);



        }

        pedido.setDetallePedidos(detallePedidosList);


        Pedido pedido1 = pedidoRepository.findFirstByOrderByIdDesc();

        pedido.setNumero(pedido1.getId()+1);


        pedido = pedidoRepository.save(pedido);


        DataResponsePedido dataResponsePedido = new DataResponsePedido(pedido.getNumero(),
                pedido.getEstado(), pedido.getTipoenvio(),pedido.getHoraestimadafin(), pedido.getTotal(),
                pedido.getCliente().getNombre(),pedido.getCliente().getApellido(), pedido.getCliente().getDomicilio().getCalle(),
                (int) pedido.getCliente().getDomicilio().getNumero(), pedido.getCliente().getDomicilio().getLocalidad());


        return ResponseEntity.ok(dataResponsePedido);

    }



    @Transactional
    @GetMapping
    public ResponseEntity<Page<DataListPedido>> pedidoList(@PageableDefault(size = 5)Pageable paginacion){



        return ResponseEntity.ok(pedidoRepository.findByEstado(Estado.A_CONFIRMAR, paginacion).map(DataListPedido::new));

    }

    @Transactional
    @PutMapping
    public ResponseEntity updatePedido(@RequestBody @Valid DataUpdatePedido dataUpdatePedido){


        Pedido pedido = pedidoRepository.getReferenceById(dataUpdatePedido.id());
        List<DataUpdateDetallePedido> detallePedidoList = dataUpdatePedido.detallePedido().stream().toList();


        for(DataUpdateDetallePedido as: detallePedidoList){

            DetallePedido detallePedido = detallePedidoRepository.getReferenceById(as.id());
            detallePedido.updateData(as);


        }



        pedido.updateData(dataUpdatePedido);

        return ResponseEntity.ok().build();



    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponsePedido> listPedidoByid(@PathVariable Long id){

    Pedido pedido = pedidoRepository.getReferenceById(id);
    DataResponsePedido dataResponsePedido = new DataResponsePedido(pedido.getNumero(),
            pedido.getEstado(), pedido.getTipoenvio(), pedido.getHoraestimadafin(),
            pedido.getTotal(), pedido.getCliente().getNombre(), pedido.getCliente().getApellido(),
            pedido.getCliente().getDomicilio().getCalle(), pedido.getCliente().getDomicilio().getNumero(),
            pedido.getCliente().getDomicilio().getLocalidad());

        return ResponseEntity.ok(dataResponsePedido);

    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity deletePedido(@PathVariable Long id){

    Pedido pedido = pedidoRepository.getReferenceById(id);
    pedido.deletePedido();
    List<DetallePedido> detallePedidos = detallePedidoRepository.findAllByPedido_id(id);
    for(DetallePedido as : detallePedidos){

        as.deleteDetallePedidos();


    }

    return ResponseEntity.noContent().build();

    }

}
