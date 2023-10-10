package gordo.alarco.api.controller;

import gordo.alarco.api.domain.address.Domicilio;
import gordo.alarco.api.domain.client.Cliente;
import gordo.alarco.api.domain.user.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    //@Autowired
    private final UserRepository userRepository;




    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DataResponseUser> registerUser (@RequestBody @Valid DataRegisterUser dataRegisterUser,
                                                          UriComponentsBuilder uriComponentsBuilder){



        User user = new User(dataRegisterUser);
        DataClient dataClient = dataRegisterUser.cliente();
        Cliente cliente = new Cliente(dataClient, user);
        user.setCliente(cliente);
        DataAddress dataAddress = dataRegisterUser.cliente().domicilio();
        Domicilio domicilio = new Domicilio(dataAddress, cliente);
        cliente.setDomicilio(domicilio);



       user = userRepository.save(user);

       DataResponseUser dataResponseUser = new DataResponseUser(user.getId(), user.getUsuario(),  user.getClave(), user.getRole(),
               new DataClient(user.getCliente().getNombre(),
               user.getCliente().getApellido(), user.getCliente().getTelefono(), user.getCliente().getEmail(),
               new DataAddress(user.getCliente().getDomicilio().getCalle(),
               user.getCliente().getDomicilio().getNumero(), user.getCliente().getDomicilio().getLocalidad())));

       URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
       return ResponseEntity.created(url).body(dataResponseUser);


    }


    @Transactional
    @GetMapping
    public ResponseEntity<Page<DataListUser>> userList(@PageableDefault(size = 2) Pageable paginacion){


        //return userRepository.findAll(paginacion).map(DataListUser::new);
        return ResponseEntity.ok(userRepository.findByActivoTrue(paginacion).map(DataListUser::new));

    }



    @Transactional
    @PutMapping
    public ResponseEntity<DataResponseUser> updateUser(@RequestBody @Valid DataUpdateUser dataUpdateUser){


        User user = userRepository.getReferenceById(dataUpdateUser.id());
        user.updateData(dataUpdateUser);
        return ResponseEntity.ok(new DataResponseUser(user.getId(), user.getUsuario(),  user.getClave(), user.getRole(),
                new DataClient(user.getCliente().getNombre(),
                        user.getCliente().getApellido(), user.getCliente().getTelefono(), user.getCliente().getEmail(),
                        new DataAddress(user.getCliente().getDomicilio().getCalle(),
                                user.getCliente().getDomicilio().getNumero(), user.getCliente().getDomicilio().getLocalidad()))));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseUser> responseDataUser(@PathVariable Long id){

        User user = userRepository.getReferenceById(id);
        DataResponseUser dataResponseUser = new DataResponseUser(user.getId(), user.getUsuario(), user.getClave(), user.getRole(),
                new DataClient(user.getCliente().getNombre(), user.getCliente().getApellido(), user.getCliente().getTelefono(), user.getCliente().getEmail(),
                        new DataAddress(user.getCliente().getDomicilio().getCalle(), user.getCliente().getDomicilio().getNumero(),
                                user.getCliente().getDomicilio().getLocalidad())));
        return ResponseEntity.ok(dataResponseUser);

    }

    //DELETE LOGICO
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){

        User user = userRepository.getReferenceById(id);
        user.deleteUser();

        return ResponseEntity.noContent().build();

    }



    //Delete FISICO
   // @Transactional
   // @DeleteMapping("/{id}")
   // public void deleteUser(@PathVariable Long id){

    //User user = userRepository.getReferenceById(id);
    //userRepository.delete(user);

   // }




}
