package ibm.ibtc.Hello_bank_G6.Secutiry;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import ibm.ibtc.Hello_bank_G6.Data.DetalheUsuarioData;
import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRATION = 1200_000; //20 MIN
    private final AuthenticationManager _authenticationManager;

    public JWTAuthFilter(AuthenticationManager authenticationManager) {
        this._authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response
    ) throws AuthenticationException {

        try {
            ClienteModel cliente = new ObjectMapper().readValue(request.getInputStream(), ClienteModel.class);

            return _authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            cliente.getCpf(),
                            cliente.getSenha(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException("Falha na autenticação " + e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult
    ) throws IOException, ServletException {
        DetalheUsuarioData clienteData = (DetalheUsuarioData) authResult.getPrincipal();

        Dotenv dotenv = Dotenv.configure().load();

        String token = JWT.create()
                .withSubject(clienteData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(Objects.requireNonNull(dotenv.get("JWT_HASH_PASS"))));

//        String res = String.format("{\n\ttoken: \"%s\"\n}", token); //Tentiva falha de retornar um JSON
//
//        response.setContentType("application/json");
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
