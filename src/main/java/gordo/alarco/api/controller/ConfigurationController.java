package gordo.alarco.api.controller;

import gordo.alarco.api.domain.configuracion.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration")
@RequiredArgsConstructor
public class ConfigurationController {

    private final ConfigurationRepository configurationRepository;



    @Transactional
    @PostMapping
    public ResponseEntity createConfiguration(@RequestBody @Valid DataRegisterConfiguration dataRegisterConfiguration){

        Configuration configuration = new Configuration(dataRegisterConfiguration);

        configuration = configurationRepository.save(configuration);

        return ResponseEntity.ok().build();

    }

    @Transactional
    @GetMapping
    public ResponseEntity listConfiguration(@PageableDefault(size = 5) Pageable paginacion){

        return ResponseEntity.ok(configurationRepository.findByActivoTrue(paginacion).map(DataListConfiguration::new));

    }

    @Transactional
    @PutMapping
    public ResponseEntity<DataResponseConfiguration> updateConfiguration(@RequestBody @Valid DataUpdateConfiguration dataUpdateConfiguration){

        Configuration configuration = configurationRepository.getReferenceById(dataUpdateConfiguration.id());
        configuration.updateData(dataUpdateConfiguration);

        return ResponseEntity.ok(new DataResponseConfiguration(dataUpdateConfiguration.cantidadCocineros(),
                dataUpdateConfiguration.emailEmpresa(), dataUpdateConfiguration.tokenMercadoPago()));

    }


    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity deleteConfiguration(@PathVariable Long id){

        Configuration configuration = configurationRepository.getReferenceById(id);
        configuration.deleteConfiguration();

        return ResponseEntity.noContent().build();

    }



}
