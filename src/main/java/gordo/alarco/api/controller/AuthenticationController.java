package gordo.alarco.api.controller;


import gordo.alarco.api.domain.user.DataAuthenticationUser;
import gordo.alarco.api.domain.user.User;
import gordo.alarco.api.infra.security.DataJWTToken;
import gordo.alarco.api.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser){

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(dataAuthenticationUser.usuario(),
                dataAuthenticationUser.clave());
       var userAuthenticade = authenticationManager.authenticate(authenticationToken);
        var JWTtoken = tokenService.generatedToken((User) userAuthenticade.getPrincipal());
        return ResponseEntity.ok(new DataJWTToken(JWTtoken));




    }

}
