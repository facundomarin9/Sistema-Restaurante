package gordo.alarco.api.domain.rubroGeneral;

import gordo.alarco.api.domain.articuloManufacturado.ArticuloManufacturado;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name="rubrogeneral")
@Entity(name="RubroGeneral")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RubroGeneral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denominacion;
    private Boolean activo;
    private LocalDate fechabaja;

    @OneToMany(mappedBy="rubrogeneral", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticuloManufacturado> articuloManufacturadoList = new ArrayList<>();

    public RubroGeneral(DataRegisterRubroGeneral dataRegisterRubroGeneral) {

        this.denominacion = dataRegisterRubroGeneral.denominacion();
        this.activo = true;

    }

    public void updateData(DataUpdateRubroGeneral dataUpdateRubroGeneral) {

        this.denominacion = dataUpdateRubroGeneral.denominacion();

    }

    public void deleteRubroGeneral() {

        this.activo = false;
        this.fechabaja = LocalDate.now();

    }
}
