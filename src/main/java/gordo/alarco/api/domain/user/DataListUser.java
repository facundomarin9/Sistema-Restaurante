package gordo.alarco.api.domain.user;

public record DataListUser(Long id, String usuario, String nombre, String apellido, String email, String localidad) {

    public DataListUser(User user){

        this(user.getId(), user.getUsuario(), user.getCliente().getNombre(), user.getCliente().getApellido(),
                user.getCliente().getEmail(), user.getCliente().getDomicilio().getLocalidad());

    }

}
