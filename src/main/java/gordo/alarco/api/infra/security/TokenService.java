package gordo.alarco.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import gordo.alarco.api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generatedToken(User user){

        //System.out.println(apiSecret);

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("raux1t")
                    .withSubject(user.getUsuario())
                    .withClaim("id",user.getId())
                    .withClaim("role", user.getRole().toString())
                    .withExpiresAt(genereatedDateExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }


    }

    public String getSubject(String token){

        if(token == null){
            throw new RuntimeException("token null");
        }

        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //Valida la firma
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("raux1t")
                    .build();
            //.verify(token);
            
            decodedJWT = verifier.verify(token);
            decodedJWT.getSubject();
            String prueba = decodedJWT.getClaim("id").toString();
            System.out.println("ESTE ES EL CLAIM ID " + Long.parseLong(prueba));
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
            throw new RuntimeException(("El token no pudo ser verificado"));
        }

        if(decodedJWT.getSubject() == null){
            throw new RuntimeException("Verifier invalid");
        }else

        System.out.println("ESTOY EN TOKEN SERVICE: " + decodedJWT.getSubject());



       return decodedJWT.getSubject();

    }

    private Instant genereatedDateExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public Long getClaimId(String token){

        String prueba = "";

        if(token == null){
            throw new RuntimeException("token null");
        }

        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //Valida la firma
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("raux1t")
                    .build();
            //.verify(token);

            decodedJWT = verifier.verify(token);
            decodedJWT.getSubject();
            prueba = decodedJWT.getClaim("id").toString();
            System.out.println("ESTE ES EL CLAIM ID " + Long.parseLong(prueba));
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
            throw new RuntimeException(("El token no pudo ser verificado"));
        }

        if(decodedJWT.getSubject() == null){
            throw new RuntimeException("Verifier invalid");
        }else

            System.out.println("ESTOY EN TOKEN SERVICE: " + decodedJWT.getSubject());

        System.out.println("ESTE ES EL CLAIM ID " + prueba);

        return Long.parseLong(prueba);

    }

}
