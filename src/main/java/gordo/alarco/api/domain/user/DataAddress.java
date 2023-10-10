package gordo.alarco.api.domain.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record DataAddress(
        @NotBlank
        String calle,
        @Min(value = 0)
        @Max(value = 9999)
        long numero,
        @NotBlank
        String localidad) {
}
