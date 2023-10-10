package gordo.alarco.api.controller;

import gordo.alarco.api.domain.articuloManufacturado.ArticuloManufacturado;
import gordo.alarco.api.domain.articuloManufacturado.ArticuloManufacturadoRepository;
import gordo.alarco.api.domain.detalleFactura.DataListFacturaDetalles;
import gordo.alarco.api.domain.detalleFactura.DataUpdateDetalleFactura;
import gordo.alarco.api.domain.detalleFactura.DetalleFactura;
import gordo.alarco.api.domain.detalleFactura.FacturaDetallesRepository;
import gordo.alarco.api.domain.detallePedido.DataUpdateDetallePedido;
import gordo.alarco.api.domain.factura.*;
import gordo.alarco.api.domain.pedido.DataResponsePedido;
import gordo.alarco.api.domain.pedido.Pedido;
import gordo.alarco.api.domain.pedido.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaRepository facturaRepository;
    private final FacturaDetallesRepository facturaDetallesRepository;
    private final PedidoRepository pedidoRepository;
    private final ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Transactional
    @PostMapping("/createFactura")
    public ResponseEntity createFactura (@RequestBody DataRegisterFactura dataRegisterFactura,
                                         UriComponentsBuilder uriComponentsBuilder) {

        Pedido pedido = pedidoRepository.getReferenceById(dataRegisterFactura.pedidoId());


        Factura factura = new Factura(dataRegisterFactura, pedido);
        List<DetalleFactura> detalleFacturasList = new ArrayList<>();

        for (DataRegisterDetalleFactura as : dataRegisterFactura.detalleFactura()) {

            DetalleFactura detalleFactura = new DetalleFactura(as);
            ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getReferenceById(as.articuloManufacturadoId());
            detalleFactura.setArticulomanufacturado(articuloManufacturado);
            detalleFactura.setFactura(factura);
            detalleFacturasList.add(detalleFactura);

        }

     pedido.setFactura(factura);


      factura.setPedido(pedido);

        factura.setDetalleFacturas(detalleFacturasList);

        Factura factura1 = facturaRepository.findFirstByOrderByIdDesc();

        factura.setNumero(factura1.getId());

        factura = facturaRepository.save(factura);


        return ResponseEntity.ok().build();
    }

    @Transactional
    @GetMapping
    public ResponseEntity <Page<DataListFactura>> facturaList(@PageableDefault(size=5)Pageable paginacion){

        return ResponseEntity.ok(facturaRepository.findAll(paginacion).map(DataListFactura::new));

    }

    @Transactional
    @GetMapping("{id}")
    public ResponseEntity<DataResponseFactura> listFacturaById(@PathVariable Long id){

        Factura factura = facturaRepository.getReferenceById(id);

        DataResponseFactura dataResponsePedido = new DataResponseFactura(factura.getNumero(), factura.getFormapago(),
                factura.getNumerotarjeta(), factura.getTotalventa(), factura.getPedido().getNumero());

        return ResponseEntity.ok(dataResponsePedido);

    }

    @Transactional
    @GetMapping("/facturaDetalles/{id}")
    public ResponseEntity<Page<DataListFacturaDetalles>> facturaDetallesList(@PageableDefault(size=5) @PathVariable Long id, Pageable paginacion){

    return ResponseEntity.ok(facturaDetallesRepository.findAllByFactura_id(id,paginacion).map(DataListFacturaDetalles::new));

    }

    @Transactional
    @PutMapping
    public ResponseEntity updateFactura(@RequestBody DataUpdateFactura dataUpdateFactura){

        Factura factura = facturaRepository.getReferenceById(dataUpdateFactura.id());
        List<DataUpdateDetalleFactura> detalleFacturas = dataUpdateFactura.detalleFactura().stream().toList();
        Pedido pedido = pedidoRepository.getReferenceById(dataUpdateFactura.pedidoId());
        factura.setPedido(pedido);

        for(DataUpdateDetalleFactura as : detalleFacturas){

            DetalleFactura detalleFactura = facturaDetallesRepository.getReferenceById(as.id());
            detalleFactura.updateData(as);

        }

        factura.updateData(dataUpdateFactura);



        return ResponseEntity.ok().build();

    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity deleteFactura(@PathVariable Long id){

        Factura factura = facturaRepository.getReferenceById(id);
        factura.deleteFactura();

        return ResponseEntity.noContent().build();

    }

}
