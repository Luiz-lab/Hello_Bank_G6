package ibm.ibtc.Hello_bank_G6.Controllers;


import ibm.ibtc.Hello_bank_G6.DTO.TranferenciaCriarDTO;
import ibm.ibtc.Hello_bank_G6.DTO.TransacaoCriarDTO;
import ibm.ibtc.Hello_bank_G6.Models.TipoTransacaoEnum;
import ibm.ibtc.Hello_bank_G6.Models.TransacaoModel;
import ibm.ibtc.Hello_bank_G6.Models.TransferenciaModel;
import ibm.ibtc.Hello_bank_G6.Repositories.IClienteRepository;
import ibm.ibtc.Hello_bank_G6.Repositories.IContaCorrenteRepository;
import ibm.ibtc.Hello_bank_G6.Repositories.ITransacaoRepository;
import ibm.ibtc.Hello_bank_G6.Repositories.ITransferenciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {
    private final IContaCorrenteRepository _contaCorrenteRepository;
    private final IClienteRepository _clienteRepository;
    private final ITransacaoRepository _transacaoRepository;
    private final ITransferenciaRepository _transferenciaRepository;

    public TransferenciaController(IContaCorrenteRepository contaCorrenteRepository,
                                   IClienteRepository clienteRepository,
                                   ITransacaoRepository transacaoRepository,
                                   ITransferenciaRepository transferenciaRepository) {
        this._contaCorrenteRepository = contaCorrenteRepository;
        this._clienteRepository = clienteRepository;
        this._transacaoRepository = transacaoRepository;
        this._transferenciaRepository = transferenciaRepository;
    }

    @PostMapping("/{param_id}")
    public ResponseEntity<Object> findById(@PathVariable String param_id) {
        var transferenciaModel = _transacaoRepository.findById(UUID.fromString(param_id));

        return (transferenciaModel.isEmpty()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
            public final Object Mensagem = "Tansação não encontrada!";
        }) : ResponseEntity.status(HttpStatus.OK).body(new Object() {
            public final Object Conta = transferenciaModel.get();
        });
    }

    @PostMapping("/AcharTransferenciasPeloRemetente/{param_id}")
    public ResponseEntity<Object> findAllByRemetente(@PathVariable String param_id){
        var cliente = _clienteRepository.findByCpf(param_id);
        if(cliente.isPresent()){
            var transferencia = _transferenciaRepository.findAllByClienteRemetente(cliente.get());
            return (transferencia.isEmpty()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object Mensagem = "Este cliente não tem transferências";
            }) : ResponseEntity.status(HttpStatus.OK).body(new Object() {
                public final Object Transferencias = transferencia.get();
            });
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object Mensagem = "Cliente não encontrado";
            });
        }
    }

    @PostMapping("/AcharTransferenciasPeloDestinatario/{param_id}")
    public ResponseEntity<Object> findAllByDestinatario(@PathVariable String param_id){
        var cliente = _clienteRepository.findByCpf(param_id);
        if(cliente.isPresent()){
            var transferencia = _transferenciaRepository.findAllByClienteDestinatario(cliente.get());
            return (transferencia.isEmpty()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object Mensagem = "Este cliente não tem transferências";
            }) : ResponseEntity.status(HttpStatus.OK).body(new Object() {
                public final Object Transferencias = transferencia.get();
            });
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object Mensagem = "Cliente não encontrado";
            });
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Object> criarTranferencia(@RequestBody TranferenciaCriarDTO tranferenciaCriarDTO) {

        var transferenciaModel = new TransferenciaModel();
        var clienteDestinatario = _clienteRepository.findByCpf(tranferenciaCriarDTO.getDestinatarioCpf());
        var clienteRemetente = _clienteRepository.findByCpf(tranferenciaCriarDTO.getRemetenteCpf());

        if (clienteDestinatario.isEmpty() || clienteRemetente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                String r = clienteDestinatario.isEmpty() ? "Destinatário" : "Remetente";
                public final Object Mensagem =  r + " não encontrado";
            });
        } else {
            transferenciaModel.setClienteRemetente(clienteRemetente.get());
            transferenciaModel.setClienteDestinatario(clienteDestinatario.get());
            transferenciaModel.setValor(tranferenciaCriarDTO.getValor());
            transferenciaModel.setCreated_at(LocalDateTime.now());

            var contaCorrenteRemetente = _contaCorrenteRepository.findByClienteModel(clienteRemetente.get());
            var contaCorrenteDestinatario = _contaCorrenteRepository.findByClienteModel(clienteDestinatario.get());

            if (contaCorrenteRemetente.isEmpty() || contaCorrenteDestinatario.isEmpty()) {
                String r = contaCorrenteRemetente.isEmpty() ? "Remetente" : "Destinatário";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                    public final Object Mensagem = r + " conta corrente não encontrada";
                });
            } else {
                if (tranferenciaCriarDTO.getValor()
                        <= (contaCorrenteRemetente.get().getSaldo())
                        + contaCorrenteRemetente.get().getLimiteNegativo()
                        && tranferenciaCriarDTO.getValor() > 0
                ) {
                    if ((contaCorrenteRemetente.get().getSaldo() - tranferenciaCriarDTO.getValor()) + contaCorrenteRemetente.get().getLimiteNegativo() < 0) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
                            public final Object Mensagem = "Não há limite disponível";
                        });
                    }
                    contaCorrenteRemetente.get().setSaldo(contaCorrenteRemetente.get().getSaldo() - tranferenciaCriarDTO.getValor());
                    contaCorrenteDestinatario.get().setSaldo(contaCorrenteDestinatario.get().getSaldo() + tranferenciaCriarDTO.getValor());

                    _contaCorrenteRepository.save(contaCorrenteDestinatario.get());
                    _contaCorrenteRepository.save(contaCorrenteRemetente.get());
                    _transferenciaRepository.save(transferenciaModel);

                    return ResponseEntity.status(HttpStatus.CREATED).body(new Object() {
                        public final Object Transferencia = transferenciaModel;
                        public final Object ContaRemetente = contaCorrenteRemetente.get();
                        public final Object ContaDestinatario = contaCorrenteDestinatario.get();
                    });

                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
                        public final Object Mensagem = "Não há limite disponível";
                    });
                }
            }
        }
    }
}
