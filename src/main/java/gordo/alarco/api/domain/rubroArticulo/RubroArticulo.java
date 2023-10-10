package gordo.alarco.api.domain.rubroArticulo;

import gordo.alarco.api.domain.articuloInsumo.ArticuloInsumo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name="rubroarticulo")
@Entity(name="RubroArticulo")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RubroArticulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denominacion;
    private Boolean activo;
    private LocalDate fechabaja;

    @OneToMany(mappedBy = "rubroarticulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticuloInsumo> articuloInsumoList = new ArrayList<>();

    public RubroArticulo(DataRegisterRubroArticulo dataRegisterRubroArticulo){

        this.denominacion = dataRegisterRubroArticulo.denominacion();
        this.activo = true;

    }

    public void updateData(DataUpdateRubroArticulo dataUpdateRubroArticulo) {

        this.denominacion =  dataUpdateRubroArticulo.denominacion();

    }

    public void deleteRubroArticulo() {

        this.activo = false;
        this.fechabaja = LocalDate.now();

    }
}
