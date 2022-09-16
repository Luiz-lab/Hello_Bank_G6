package ibm.ibtc.Hello_bank_G6.Models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "transacao_t")
public class TransacaoModel {
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "c_transacao_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "c_tipo_transacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTransacaoEnum tipoTransacao;

    @Column(name = "c_valor", nullable = false)
    private Double valor;

    @ManyToOne()
    @JoinColumn (name = "c_cliente_id", referencedColumnName= "c_cliente_id", nullable = false)
    private ClienteModel cliente;

    @Column(name = "c_created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "c_updated_at")
    private LocalDateTime updated_at;
}
