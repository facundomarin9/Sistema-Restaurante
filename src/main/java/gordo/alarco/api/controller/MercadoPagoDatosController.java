package gordo.alarco.api.controller;

import gordo.alarco.api.domain.mercadoPagoDatos.*;
import gordo.alarco.api.domain.pedido.Pedido;
import gordo.alarco.api.domain.pedido.PedidoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mercadoPagoDatos")
@RequiredArgsConstructor
public class MercadoPagoDatosController {

    private final MercadoPagoDatosRepository mercadoPagoDatosRepository;
    private final PedidoRepository pedidoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<DataResponseMPDatos> createMercadoPagoDatos(@RequestBody @Valid DataRegisterMPDatos dataRegisterMPDatos){


        MercadoPagoDatos mercadoPagoDatos = new MercadoPagoDatos(dataRegisterMPDatos);
        Pedido pedido = pedidoRepository.getReferenceById(dataRegisterMPDatos.pedidoId());
        mercadoPagoDatos.setPedido(pedido);
        mercadoPagoDatos = mercadoPagoDatosRepository.save(mercadoPagoDatos);

        return ResponseEntity.ok(new DataResponseMPDatos(mercadoPagoDatos.getFechacreacion(), mercadoPagoDatos.getFechaaprobacion(), mercadoPagoDatos.getFormapago(),
                mercadoPagoDatos.getMetodopago(), mercadoPagoDatos.getNumerotarjeta(), mercadoPagoDatos.getEstado(), pedido.getId()));

    }

    @Transactional
    @GetMapping
    public ResponseEntity<Page<DataListMPDatos>> listMercadoPagoDatos(@PageableDefault(size = 5)Pageable paginacion){

        return ResponseEntity.ok(mercadoPagoDatosRepository.findAll(paginacion).map(DataListMPDatos::new));

    }

    @Transactional
    @PutMapping
    public ResponseEntity updateMercadoPagoDatos(@RequestBody @Valid DataUpdateMPDatos dataUpdateMPDatos){

        MercadoPagoDatos mercadoPagoDatos = mercadoPagoDatosRepository.getReferenceById(dataUpdateMPDatos.id());

        Pedido pedido = pedidoRepository.getReferenceById(dataUpdateMPDatos.pedido().id());
        if(pedido.getActivo()){
            mercadoPagoDatos.setPedido(pedido);
            mercadoPagoDatos.updateData(dataUpdateMPDatos);
        }


        return ResponseEntity.ok().build();

    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity deleteMercadoPagoDatos(@PathVariable Long id){

        MercadoPagoDatos mercadoPagoDatos = mercadoPagoDatosRepository.getReferenceById(id);
        mercadoPagoDatos.deleteMercadoPagoDatos();

        return ResponseEntity.noContent().build();

    }

}
