package gordo.alarco.api.infra.security;

import gordo.alarco.api.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Obtener token del header
        var authHeader = request.getHeader("Authorization");


        if(authHeader != null ){

            var token = authHeader.replace("Bearer ", "");
            var subject = tokenService.getSubject(token);
            Long claimId = tokenService.getClaimId(token);
            request.setAttribute("claimId", claimId);
            //System.out.println("ESTOY EN SECURITY FILTER: " + claimId);

            if(subject != null){
                var user = userRepository.findByUsuario(subject);
                var authentication = new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }




        filterChain.doFilter(request, response);

    }
}
