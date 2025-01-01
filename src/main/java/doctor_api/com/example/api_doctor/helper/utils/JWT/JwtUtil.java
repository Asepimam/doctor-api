package doctor_api.com.example.api_doctor.helper.utils.JWT;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private  String SECRET_KEY;
    @Value("${jwt.issuer}")
    private  String ISSUER;

    public String genereteToken(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer(ISSUER)
                .withExpiresAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60000))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String validateToken(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .withIssuer(ISSUER)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = validateToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

}
