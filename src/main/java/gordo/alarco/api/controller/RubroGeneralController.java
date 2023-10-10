package gordo.alarco.api.controller;

import gordo.alarco.api.domain.rubroGeneral.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rubroGeneral")
@RequiredArgsConstructor
public class RubroGeneralController {

    private final RubroGeneralRepository rubroGeneralRepository;





        @Transactional
        @PostMapping("/createRubroGeneral")
        public ResponseEntity<DataResponseRubroGeneral> createRubroGeneral(@RequestBody DataRegisterRubroGeneral dataRegisterRubroGeneral){

            RubroGeneral rubroGeneral = new RubroGeneral(dataRegisterRubroGeneral);
            rubroGeneral = rubroGeneralRepository.save(rubroGeneral);
            DataResponseRubroGeneral dataResponseRubroGeneral = new DataResponseRubroGeneral(dataRegisterRubroGeneral.denominacion());

            return ResponseEntity.ok(dataResponseRubroGeneral);

        }

        @Transactional
        @GetMapping
        public ResponseEntity<Page<DataListRubroGeneral>> listRubroGeneral(@PageableDefault(size = 5) Pageable paginacion){

            return ResponseEntity.ok(rubroGeneralRepository.findAll(paginacion).map(DataListRubroGeneral::new));



        }

        @Transactional
        @GetMapping("/{id}")
        public ResponseEntity<DataResponseRubroGeneral> listRubroGeneralById(@PathVariable Long id){

            RubroGeneral rubroGeneral = rubroGeneralRepository.getReferenceById(id);
            DataResponseRubroGeneral dataResponseRubroGeneral = new DataResponseRubroGeneral(rubroGeneral.getDenominacion());
            return ResponseEntity.ok(dataResponseRubroGeneral);
        }

        @Transactional
        @PutMapping
        public ResponseEntity updateRubroGeneral(@RequestBody DataUpdateRubroGeneral dataUpdateRubroGeneral){

            RubroGeneral rubroGeneral = rubroGeneralRepository.getReferenceById(dataUpdateRubroGeneral.id());
            rubroGeneral.updateData(dataUpdateRubroGeneral);

            return ResponseEntity.ok().build();


        }

        @Transactional
        @DeleteMapping("/{id}")
        public ResponseEntity deleteRubroGeneral(@PathVariable Long id){

            RubroGeneral rubroGeneral = rubroGeneralRepository.getReferenceById(id);
            rubroGeneral.deleteRubroGeneral();

            return ResponseEntity.noContent().build();
        }





}
