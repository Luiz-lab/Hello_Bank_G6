package ibm.ibtc.Hello_bank_G6.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Date;
import java.util.Objects;

public class JWTGenerator {
    public String gerarJWT(String cpf){
        Dotenv dotenv = Dotenv.configure().load();

        return JWT.create()
                .withSubject(cpf)
                .withExpiresAt(new Date(System.currentTimeMillis() + 600_000))
                .sign(Algorithm.HMAC512(Objects.requireNonNull(dotenv.get("JWT_HASH_PASS"))));
    }
}
