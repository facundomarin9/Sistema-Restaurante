package gordo.alarco.api.domain.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record DataClient(
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,

        @Min(value = 0)
        @Max(value = 999999999)
        Long telefono,
        @NotBlank
        @Email
        String email,
        @NotNull
        @Valid
        DataAddress domicilio){
}
