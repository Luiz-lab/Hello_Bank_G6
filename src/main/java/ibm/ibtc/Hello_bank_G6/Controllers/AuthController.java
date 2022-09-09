package ibm.ibtc.Hello_bank_G6.Controllers;

import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;
import ibm.ibtc.Hello_bank_G6.Repositories.IClienteRepository;
import ibm.ibtc.Hello_bank_G6.ViewModels.ClienteVizualizacaoViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IClienteRepository _clienteRepository;
    private final PasswordEncoder encoder;

    public AuthController(IClienteRepository clienteRepository, PasswordEncoder encoder) {
        this._clienteRepository = clienteRepository;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> Auth(@RequestBody Map<String, Object> req){
        Optional<ClienteModel> cliente;
        if ((req.get("email") != null || req.get("cpf") != null) && req.get("senha") != null) {

            if(req.get("email") != null) cliente = _clienteRepository.findByEmail(String.valueOf(req.get("email")));
            else cliente = _clienteRepository.findByCpf(String.valueOf(req.get("cpf")));

            if(cliente.isPresent()){

                if (encoder.matches((CharSequence) req.get("senha"), cliente.get().getSenha())) {

                    var clienteView = new ClienteVizualizacaoViewModel();
                    BeanUtils.copyProperties(cliente.get(), clienteView);

                    return ResponseEntity.status(HttpStatus.OK).body(new Object() {
                        public final Object cliente = clienteView;
                    });

                }else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Object() {
                        public final Object mensagem = "Senha incorreta";
                    });
                }

            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
                    public final Object mensagem = "Usuário não encontrado";
                });
            }

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
            public final Object mensagem = "Campo faltante";
        });
    }
}
