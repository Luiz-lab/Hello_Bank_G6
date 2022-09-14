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

    //Retirar posteriormente
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Object() {
            public final Object contasCorrente = _contaCorrenteRepository.findAll();
        });
    }

    @PostMapping("/{param_id}")
    public ResponseEntity<Object> findById(@PathVariable String param_id){
        var contaCorrente = _contaCorrenteRepository.findById(UUID.fromString(param_id));

        return (contaCorrente.isEmpty()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
            public final Object Mensagem = "Conta não encontrada.";
        }) : ResponseEntity.status(HttpStatus.OK).body(new Object() {
            public final Object Cliente = contaCorrente.get();
        });

    }

//    @PostMapping("/criar")
//    public ResponseEntity<Object> createAnimal(@RequestBody ContaCorrenteModel contaCorrenteModel) {
//
//        contaCorrenteModel.setCreated_at(LocalDateTime.now());
//        contaCorrenteModel.setUpdated_at(LocalDateTime.now());
//
//        _contaCorrenteRepository.save(contaCorrenteModel);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(new Object() {
//            public final Object contaCorrente = contaCorrenteModel;
//        });
//    }

//    @DeleteMapping("/deleteById/{param_id}")
//    public ResponseEntity<Object> deleteById(@PathVariable String param_id) {
//
//        var contaCorrente = _contaCorrenteRepository.findById(UUID.fromString(param_id));
//
//        if (contaCorrente.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
//                public final Object message = "Conta não encontrado";
//            });
//        } else {
//            _contaCorrenteRepository.deleteById(UUID.fromString(param_id));
//
//            return ResponseEntity.status(HttpStatus.OK).body(new Object() {
//                public final Object message = "Conta deletado";
//            });
//        }
//    }

    @PutMapping("/updateContaCorrente/{param_id}")
    public ResponseEntity<Object> updateCliente(@PathVariable String param_id, @RequestBody Map<String, Object> req) {

        var contaCorrente = _contaCorrenteRepository.findById(UUID.fromString(param_id));

        if (contaCorrente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object message = "Conta não encontrado";
            });
        } else {
            var contaCorrenteResult = new ContaCorrenteModel();
            BeanUtils.copyProperties(contaCorrente.get(), contaCorrenteResult);

            if (req.get("saldo") != null) contaCorrenteResult.setSaldo((Double) req.get("saldo"));
            if (req.get("limiteNegativo") != null) contaCorrenteResult.setSaldo((Double) req.get("limiteNegativo"));

            contaCorrenteResult.setUpdated_at(LocalDateTime.now());

            _contaCorrenteRepository.save(contaCorrenteResult);

            return ResponseEntity.status(HttpStatus.OK).body(new Object() {
                public final Object contaCorrente = contaCorrenteResult;
            });
        }
    }
}
