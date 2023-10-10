package gordo.alarco.api.domain.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterUser(
        @NotBlank
        String usuario,
        @NotBlank
        String clave,
        @NotNull
        Role role,
        @NotNull
        @Valid
        DataClient cliente) {
}
