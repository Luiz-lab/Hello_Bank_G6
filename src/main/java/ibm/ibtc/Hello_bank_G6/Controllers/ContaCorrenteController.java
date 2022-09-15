package ibm.ibtc.Hello_bank_G6.Controllers;


import ibm.ibtc.Hello_bank_G6.Models.ContaCorrenteModel;
import ibm.ibtc.Hello_bank_G6.Repositories.IContaCorrenteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/contaCorrente")
public class ContaCorrenteController {
    private final IContaCorrenteRepository _contaCorrenteRepository;

    public ContaCorrenteController(IContaCorrenteRepository contaCorrenteRepository) {
        this._contaCorrenteRepository = contaCorrenteRepository;
    }

    @PostMapping("/{param_id}")
    public ResponseEntity<Object> findById(@PathVariable String param_id){
        var contaCorrente = _contaCorrenteRepository.findById(UUID.fromString(param_id));

        return (contaCorrente.isEmpty()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
            public final Object Mensagem = "Conta não encontrada!";
        }) : ResponseEntity.status(HttpStatus.OK).body(new Object() {
            public final Object Conta = contaCorrente.get();
        });

    }

    @PutMapping("/updateContaCorrente/{param_id}")
    public ResponseEntity<Object> updateCliente(@PathVariable String param_id, @RequestBody Map<String, Object> req) {

        var contaCorrente = _contaCorrenteRepository.findById(UUID.fromString(param_id));

        if (contaCorrente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object Mensagem = "Conta não encontrado";
            });
        } else {

            if (req.get("saldo") != null) contaCorrente.get().setSaldo((double) req.get("saldo"));
            if (req.get("limiteNegativo") != null) contaCorrente.get().setSaldo((double) req.get("limiteNegativo"));

            contaCorrente.get().setUpdated_at(LocalDateTime.now());

            _contaCorrenteRepository.save(contaCorrente.get());

            return ResponseEntity.status(HttpStatus.OK).body(new Object() {
                public final Object ContaCorrente = contaCorrente.get();
            });
        }
    }
}
