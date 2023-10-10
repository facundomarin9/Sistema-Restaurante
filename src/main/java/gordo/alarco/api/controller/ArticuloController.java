package gordo.alarco.api.controller;

import gordo.alarco.api.domain.articuloInsumo.*;
import gordo.alarco.api.domain.pedido.DataResponsePedido;
import gordo.alarco.api.domain.rubroArticulo.RubroArticulo;
import gordo.alarco.api.domain.rubroArticulo.RubroArticuloRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/articulos")
@RequiredArgsConstructor
public class ArticuloController {

    private final ArticuloInsumoRepository articuloInsumoRepository;
    private final RubroArticuloRepository rubroArticuloRepository;



    @Transactional
    @PostMapping("/createArticuloInsumo")
    public ResponseEntity registerArticuloInsumo(@RequestBody @Valid DataRegisterArticuloInsumo dataRegisterArticuloInsumo,
                                                 UriComponentsBuilder uriComponentsBuilder){

        ArticuloInsumo articuloInsumo = new ArticuloInsumo(dataRegisterArticuloInsumo);
        articuloInsumo.setRubroarticulo(dataRegisterArticuloInsumo.rubroArticulo());
        articuloInsumo = articuloInsumoRepository.save(articuloInsumo);

        return ResponseEntity.ok(articuloInsumo);


    }

    @Transactional
    @GetMapping("/listArticulosInsumos")
    public ResponseEntity<Page<DataListArticuloInsumo>> ListArticuloInsumo(@PageableDefault(size = 5)Pageable paginacion){



        return ResponseEntity.ok(articuloInsumoRepository.findByEsinsumo(true, paginacion).map(DataListArticuloInsumo::new));

    }

    @Transactional
    @GetMapping("{id}")
    public ResponseEntity<DataResponseArticulo> listArticuloById(@PathVariable Long id){

        ArticuloInsumo articuloInsumo = articuloInsumoRepository.getReferenceById(id);
        RubroArticulo rubroArticulo = rubroArticuloRepository.getReferenceById(articuloInsumo.getRubroarticulo().getId());

        DataResponseArticulo dataResponseArticulo = new DataResponseArticulo(articuloInsumo.getId(), articuloInsumo.getDenominacion(),
                articuloInsumo.getPreciocompra(), articuloInsumo.getPrecioventa(), articuloInsumo.getStockactual(),
                articuloInsumo.getStockminimo(), articuloInsumo.getUnidadmedida(), articuloInsumo.isEsinsumo(),
                rubroArticulo.getDenominacion());


        return ResponseEntity.ok(dataResponseArticulo);

    }

    @Transactional
    @PutMapping
    public ResponseEntity updateArticuloInsumo(@RequestBody @Valid DataUpdateArticuloInsumo dataUpdateArticuloInsumo){

        ArticuloInsumo articuloInsumo = articuloInsumoRepository.getReferenceById(dataUpdateArticuloInsumo.id());
        RubroArticulo rubroArticulo = rubroArticuloRepository.getReferenceById(dataUpdateArticuloInsumo.rubroArticulo().getId());
        articuloInsumo.updateData(dataUpdateArticuloInsumo);
        articuloInsumo.setRubroarticulo(rubroArticulo);

        return ResponseEntity.ok().build();

    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity deleteArticuloInsumo(@PathVariable Long id){

        ArticuloInsumo articuloInsumo = articuloInsumoRepository.getReferenceById(id);
        articuloInsumo.deleteArticuloInsumo();

        return ResponseEntity.noContent().build();

    }



}
