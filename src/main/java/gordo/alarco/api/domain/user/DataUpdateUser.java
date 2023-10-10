package gordo.alarco.api.domain.user;

import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(@NotNull Long id, String clave, DataClient cliente, DataAddress direccion ) {




}
