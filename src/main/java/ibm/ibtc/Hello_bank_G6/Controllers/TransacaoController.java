package ibm.ibtc.Hello_bank_G6.Controllers;


import ibm.ibtc.Hello_bank_G6.DTO.ClienteCriarDTO;
import ibm.ibtc.Hello_bank_G6.DTO.TransacaoCriarDTO;
import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;
import ibm.ibtc.Hello_bank_G6.Models.ContaCorrenteModel;
import ibm.ibtc.Hello_bank_G6.Models.TipoTransacaoEnum;
import ibm.ibtc.Hello_bank_G6.Models.TransacaoModel;
import ibm.ibtc.Hello_bank_G6.Repositories.IClienteRepository;
import ibm.ibtc.Hello_bank_G6.Repositories.IContaCorrenteRepository;
import ibm.ibtc.Hello_bank_G6.Repositories.ITransacaoRepository;
import ibm.ibtc.Hello_bank_G6.Utils.JWTGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private final IContaCorrenteRepository _contaCorrenteRepository;
    private final IClienteRepository _clienteRepository;
    private final ITransacaoRepository _transacaoRepository;

    public TransacaoController(IContaCorrenteRepository contaCorrenteRepository,
                               IClienteRepository clienteRepository,
                               ITransacaoRepository transacaoRepository) {
        this._contaCorrenteRepository = contaCorrenteRepository;
        this._clienteRepository = clienteRepository;
        this._transacaoRepository = transacaoRepository;
    }

    @PostMapping("/ClienteCpf/{param_id}")
    public ResponseEntity<Object> findById(@PathVariable String param_id){
        var cliente = _clienteRepository.findByCpf(param_id);
        if(cliente.isPresent()){
            var transacoes = _transacaoRepository.findAllByCliente(cliente.get());
            return (transacoes.isEmpty()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object Mensagem = "Tansação não encontrada!";
            }) : ResponseEntity.status(HttpStatus.OK).body(new Object() {
                public final Object Transacoes = transacoes.get();
            });
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object Mensagem = "Cliente não encontrado";
            });
        }
    }

    @PostMapping("/{param_id}")
    public ResponseEntity<Object> findByCliente(@PathVariable String param_id){
        var transacaoModel = _transacaoRepository.findById(UUID.fromString(param_id));

        return (transacaoModel.isEmpty()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
            public final Object Mensagem = "Tansação não encontrada!";
        }) : ResponseEntity.status(HttpStatus.OK).body(new Object() {
            public final Object Conta = transacaoModel.get();
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

    @PostMapping("/criar")
    public ResponseEntity<Object> criarTransacao(@RequestBody TransacaoCriarDTO transacaoCriarDTO) {

        var transacaoModel = new TransacaoModel();

        var clienteModel = _clienteRepository.findById(UUID.fromString(transacaoCriarDTO.getClienteId()));

        if (clienteModel.isPresent()) {
            var contaCorrenteModel = _contaCorrenteRepository.findByClienteModel(clienteModel.get());

            if (contaCorrenteModel.isPresent()) {
                try {
                    transacaoModel.setTipoTransacao(TipoTransacaoEnum.valueOf(transacaoCriarDTO.getTipoTransacao()));
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                        public final Object Mensagem = "Tipo transação incorreto -> Exemplos corretos: \"SAQUE\", \"DEPOSITO\"";
                        public final Object Erro = e;
                    });
                }
                transacaoModel.setValor(transacaoCriarDTO.getValor());
                transacaoModel.setCreated_at(LocalDateTime.now());
                transacaoModel.setCliente(clienteModel.get());

                if (transacaoModel.getTipoTransacao() == TipoTransacaoEnum.DEPOSITO) {
                    contaCorrenteModel.get().setSaldo(contaCorrenteModel.get().getSaldo() + transacaoModel.getValor());
                }
                else if (transacaoModel.getTipoTransacao() == TipoTransacaoEnum.SAQUE) {

                    if (transacaoModel.getValor() <= (contaCorrenteModel.get().getSaldo()) + contaCorrenteModel.get().getLimiteNegativo()) {
                        if ((contaCorrenteModel.get().getSaldo() - transacaoModel.getValor()) + contaCorrenteModel.get().getLimiteNegativo() < 0) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
                                public final Object Mensagem = "Não há limite disponível";
                            });
                        }
                        contaCorrenteModel.get().setSaldo(contaCorrenteModel.get().getSaldo() - transacaoModel.getValor());
                    }else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Object() {
                            public final Object Mensagem = "Não há limite disponível";
                        });
                    }
                }else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Object() {
                        public final Object Mensagem = "Erro de enum tipoTransacao";
                    });
                }

                _contaCorrenteRepository.save(contaCorrenteModel.get());
                _transacaoRepository.save(transacaoModel);

                return ResponseEntity.status(HttpStatus.CREATED).body(new Object() {
                    public final Object Transacao = transacaoModel;
                    public final Object ContaCorrente = contaCorrenteModel;
                });

            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                    public final Object Mensagem = "ContaCorrente associada ao clienteId não encontrado";
                });
            }


        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final Object Mensagem = "Cliente não encontrado";
            });
        }
    }
}
