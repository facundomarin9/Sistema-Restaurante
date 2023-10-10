package gordo.alarco.api.domain.user;

//TENGO QUE BORRAR EL ATRIBUTO CLAVE EN LA PARTE FINAL DEL PROYECTO

public record DataResponseUser(Long id,
                               String Usuario,
                               String clave,
                               Role role,
                               DataClient cliente) {
}
