package gordo.alarco.api.controller;

import gordo.alarco.api.domain.rubroArticulo.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/rubroArticulos")
@RequiredArgsConstructor
public class RubroArticuloController {

    private final RubroArticuloRepository rubroArticuloRepository;


    @Transactional
    @PostMapping("/createRubroArticulo")
    public ResponseEntity registerRubroArticulo(@RequestBody @Valid DataRegisterRubroArticulo dataRegisterRubroArticulo,
                                                UriComponentsBuilder uriComponentsBuilder){

        RubroArticulo rubroArticulo = new RubroArticulo(dataRegisterRubroArticulo);
        rubroArticulo = rubroArticuloRepository.save(rubroArticulo);

        return ResponseEntity.ok(rubroArticulo);

    }

    @Transactional
    @GetMapping("/listRubrosArticulos")
    public ResponseEntity<Page<DataListRubroArticulo>> listRubroArticulo(@PageableDefault(size = 5)Pageable paginacion){

        return ResponseEntity.ok(rubroArticuloRepository.findAll(paginacion).map(DataListRubroArticulo::new));

    }

    @Transactional
    @GetMapping("{id}")
    public ResponseEntity<DataResponseRubroArticulo> listRubroArticuloById(@PathVariable Long id){

        RubroArticulo rubroArticulo = rubroArticuloRepository.getReferenceById(id);

        DataResponseRubroArticulo dataResponseRubroArticulo = new DataResponseRubroArticulo(
                rubroArticulo.getId(),rubroArticulo.getDenominacion()
        );

        return ResponseEntity.ok(dataResponseRubroArticulo);

    }

    @Transactional
    @PutMapping
    public ResponseEntity updateRubroArticulo(@RequestBody @Valid DataUpdateRubroArticulo dataUpdateRubroArticulo){

        RubroArticulo rubroArticulo = rubroArticuloRepository.getReferenceById(dataUpdateRubroArticulo.id());

        rubroArticulo.updateData(dataUpdateRubroArticulo);

        return ResponseEntity.ok().build();


    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity deleteRubroArticulo(@PathVariable Long id){

        RubroArticulo rubroArticulo = rubroArticuloRepository.getReferenceById(id);
        rubroArticulo.deleteRubroArticulo();

        return ResponseEntity.noContent().build();

    }



}
