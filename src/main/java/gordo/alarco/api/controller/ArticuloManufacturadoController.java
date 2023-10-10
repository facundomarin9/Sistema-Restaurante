package gordo.alarco.api.controller;

import gordo.alarco.api.domain.articuloInsumo.ArticuloInsumo;
import gordo.alarco.api.domain.articuloInsumo.ArticuloInsumoRepository;
import gordo.alarco.api.domain.articuloInsumo.DataListArticuloInsumo;
import gordo.alarco.api.domain.articuloInsumo.DataRegisterArticuloInsumo;
import gordo.alarco.api.domain.articuloManufacturado.*;
import gordo.alarco.api.domain.articuloManufacturadoDetalle.*;
import gordo.alarco.api.domain.rubroArticulo.DataRegisterRubroArticulo;
import gordo.alarco.api.domain.rubroArticulo.RubroArticulo;
import gordo.alarco.api.domain.rubroArticulo.RubroArticuloRepository;
import gordo.alarco.api.domain.rubroGeneral.RubroGeneral;
import gordo.alarco.api.domain.rubroGeneral.RubroGeneralRepository;
import jakarta.validation.Valid;
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
@RequestMapping("/articulosManufacturados")
@RequiredArgsConstructor
public class ArticuloManufacturadoController {

    private final ArticuloManufacturadoRepository articuloManufacturadoRepository;
    private final ArticuloInsumoRepository articuloInsumoRepository;
    private final ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;
    private final RubroGeneralRepository rubroGeneralRepository;


    @Transactional
    @PostMapping("/createArticuloManufacturado")
    public ResponseEntity registerArticuloManufacturado(@RequestBody @Valid DataRegisterArticuloManufacturado dataRegisterArticuloManufacturado,
                                                        UriComponentsBuilder uriComponentsBuilder){

        RubroGeneral rubroGeneral = rubroGeneralRepository.getReferenceById(dataRegisterArticuloManufacturado.rubroGeneralId());
        ArticuloManufacturado articuloManufacturado = new ArticuloManufacturado(dataRegisterArticuloManufacturado);
        articuloManufacturado.setRubrogeneral(rubroGeneral);
        List<ArticuloManufacturadoDetalle> articuloManufacturadoDetalleList = new ArrayList<>();

        for(DataRegisterArticuloManufacturadoDetalle data : dataRegisterArticuloManufacturado.articuloManufacturadoDetalle().stream().toList()){

            ArticuloManufacturadoDetalle articuloManufacturadoDetalle = new ArticuloManufacturadoDetalle(data);
            articuloManufacturadoDetalle.setArticulomanufacturado(articuloManufacturado);

            ArticuloInsumo articuloInsumo = articuloInsumoRepository.getReferenceById(data.articuloInsumoId());

                articuloInsumo.getDenominacion();
                articuloManufacturadoDetalle.setArticuloInsumo(articuloInsumo);
                articuloManufacturadoDetalleList.add(articuloManufacturadoDetalle);

        }



        articuloManufacturado.setArticuloManufacturadoDetalleList(articuloManufacturadoDetalleList);

        articuloManufacturado = articuloManufacturadoRepository.save(articuloManufacturado);


    return ResponseEntity.ok().build();

    }

    @Transactional
    @GetMapping
    public ResponseEntity<Page<DataListArticuloManufacturado>> listArticuloManufacturado(@PageableDefault(size = 5)Pageable paginacion){




    return ResponseEntity.ok(articuloManufacturadoRepository.findAll(paginacion).map(DataListArticuloManufacturado::new));


    }

    @Transactional
    @GetMapping("/detalles/{id}")
    public ResponseEntity <List<DataListArticuloManufacturadoDetalle>> listArticuloManufacturadoDetalle(@PathVariable Long id){

        List<ArticuloManufacturadoDetalle> articuloManufacturadoDetalleList = articuloManufacturadoDetalleRepository.findAllByArticulomanufacturado_id(id);
        List<DataListArticuloManufacturadoDetalle> dataListArticuloManufacturadoDetalles = new ArrayList<>();

        for (ArticuloManufacturadoDetalle as: articuloManufacturadoDetalleList){


            DataListArticuloManufacturadoDetalle detalle = new DataListArticuloManufacturadoDetalle(as.getCantidad(), as.getUnidadmedida(), as.getArticuloInsumo().getDenominacion());
            dataListArticuloManufacturadoDetalles.add(detalle);
        }

    return ResponseEntity.ok(dataListArticuloManufacturadoDetalles);
    }

    @Transactional
    @GetMapping("{id}")
    public ResponseEntity<DataResponseArticuloManufacturado> listArticuloManufacturadoById(@PathVariable Long id){

        ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getReferenceById(id);
        List<DataResponseArticuloManufacturadoDetalles> dataResponseAmD = new ArrayList<>();
        for(ArticuloManufacturadoDetalle as: articuloManufacturado.getArticuloManufacturadoDetalleList()){

            DataResponseArticuloManufacturadoDetalles dataResponseArticuloManufacturadoDetalles = new DataResponseArticuloManufacturadoDetalles(
                    as.getCantidad(),as.getUnidadmedida(),as.getArticuloInsumo().getDenominacion()
            );

            dataResponseAmD.add(dataResponseArticuloManufacturadoDetalles);

        }



        DataResponseArticuloManufacturado dataResponseArticuloManufacturado = new DataResponseArticuloManufacturado(
                articuloManufacturado.getTiempoestimadococina(), articuloManufacturado.getDenominacion(), articuloManufacturado.getPrecioventa(),
                articuloManufacturado.getImagen(), dataResponseAmD
        );

        return ResponseEntity.ok(dataResponseArticuloManufacturado);

    }

    @Transactional
    @PutMapping
    public ResponseEntity updateArticuloManufacturado(@RequestBody @Valid DataUpdateAriculoManufacturado dataUpdateAriculoManufacturado){


        ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getReferenceById(dataUpdateAriculoManufacturado.id());
        List<DataUpdateDetalleAM> detalleAMList = dataUpdateAriculoManufacturado.detallesAMList().stream().toList();

        for(DataUpdateDetalleAM as : detalleAMList){

            ArticuloManufacturadoDetalle articuloManufacturadoDetalle = articuloManufacturadoDetalleRepository.getReferenceById(as.id());
            articuloManufacturadoDetalle.updateData(as);

        }

        articuloManufacturado.updateData(dataUpdateAriculoManufacturado);


        return ResponseEntity.ok().build();

    }

    @Transactional
    @DeleteMapping("/deleteArtMan/{id}")
    public ResponseEntity deleteArticuloManufacturado(@PathVariable Long id){

        ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getReferenceById(id);

        articuloManufacturado.deleteArticuloManufacturado();

        return ResponseEntity.noContent().build();

    }

    @Transactional
    @DeleteMapping("/deleteDetallesAM/{id}")
    public ResponseEntity deleteArticuloManufacturado(@PathVariable Long id, String a){

        ArticuloManufacturadoDetalle articuloManufacturadoDetalle = articuloManufacturadoDetalleRepository.getReferenceById(id);
        articuloManufacturadoDetalleRepository.delete(articuloManufacturadoDetalle);

        return ResponseEntity.noContent().build();
    }





}
