package ibm.ibtc.Hello_bank_G6.Models;

import lombok.Cleanup;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "transacao_t")
public class TransacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_transacao_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "c_tipo_transacao", nullable = false)
    private String tipoTransacao;

    @Column(name = "c_valor", nullable = false)
    private Double valor;

    @Column(name = "c_saldo_anterior", nullable = false)
    private Double saldoAnterior;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "c_cliente_id", referencedColumnName= "c_cliente_id", nullable = false)
    private ClienteModel cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "c_transferencia_id", referencedColumnName= "c_transferencia_id", nullable = true)
    private TransferenciaModel transferencia;

    @Column(name = "c_created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "c_updated_at")
    private LocalDateTime updated_at;
}
