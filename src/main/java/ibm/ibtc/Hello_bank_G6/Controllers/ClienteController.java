package ibm.ibtc.Hello_bank_G6.Controllers;


import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;
import ibm.ibtc.Hello_bank_G6.Repositories.IClienteRepository;
import ibm.ibtc.Hello_bank_G6.ViewModels.ClienteReqCriarViewModel;
import ibm.ibtc.Hello_bank_G6.ViewModels.ClienteVizualizacaoViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final IClienteRepository _clienteRepository;
    private final PasswordEncoder encoder;

    public ClienteController(IClienteRepository clienteRepository, PasswordEncoder encoder) {
        this._clienteRepository = clienteRepository;
        this.encoder = encoder;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Object() {
            public final Object clientes = _clienteRepository.findAll();
        });
    }

    @PostMapping("/{param_id}")
    public ResponseEntity<Object> findById(@PathVariable String param_id){
        var cliente = _clienteRepository.findById(UUID.fromString(param_id));


        return (cliente.isPresent()) ? ResponseEntity.status(HttpStatus.OK).body(new Object() {
            public final Object Cliente = cliente.get();
        }) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
            public final Object Mensagem = "Cliente inexistente.";
        });

    }

    @PostMapping("/criar")
    public ResponseEntity<Object> createAnimal(@RequestBody ClienteReqCriarViewModel clienteReq) {

        var clienteModel = new ClienteModel();
        var clienteView = new ClienteVizualizacaoViewModel();
        BeanUtils.copyProperties(clienteReq, clienteModel);
        BeanUtils.copyProperties(clienteReq, clienteView);

        clienteModel.setCreated_at(LocalDateTime.now());
        clienteModel.setUpdated_at(LocalDateTime.now());
        clienteModel.setSenha(encoder.encode(clienteModel.getSenha()));

        _clienteRepository.save(clienteModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Object() {
            public final Object cliente = clienteView;
        });



    }

//    @DeleteMapping("/deleteById/{param_id}")
//    public ResponseEntity<Object> deleteById(@PathVariable int param_id) {
//
//        Optional<ClienteModel> cliente = _clienteRepository.findById(param_id);
//
//        if (cliente.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
//                public final Object message = "Cliente não encontrado";
//            });
//        } else {
//            _clienteRepository.deleteById(param_id);
//
//            return ResponseEntity.status(HttpStatus.OK).body(new Object() {
//                public final Object message = "Cliente deletado";
//            });
//        }
//    }
//
//    @PutMapping("/updateCustomer/{param_id}")
//    public ResponseEntity<Object> updateCliente(@PathVariable int param_id, @RequestBody Map<String, Object> req) {
//
//        Optional<ClienteModel> clienteModel = _clienteRepository.findById(param_id);
//
//        if (clienteModel.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
//                public final Object message = "Cliente not found!";
//            });
//        } else {
//            ClienteModel clienteResult = new ClienteModel();
//            BeanUtils.copyProperties(clienteModel.get(), clienteResult);
//
//            if (req.get("nome") != null) clienteResult.setNome((String) req.get("nome"));
//            if (req.get("email") != null) clienteResult.setEmail((String) req.get("email"));
//            if (req.get("tel") != null) clienteResult.setTel((String) req.get("tel"));
//            if (req.get("endereco") != null) clienteResult.setEndereco((String) req.get("endereco"));
//
//            clienteResult.setUpdated_at(LocalDateTime.now());
//
//            _clienteRepository.save(clienteResult);
//
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Object() {
//                public final Object cliente = clienteResult;
//            });
//        }
//
//    }
}
