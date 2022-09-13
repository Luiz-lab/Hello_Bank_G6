package ibm.ibtc.Hello_bank_G6.Models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "transferencia_t")
public class TransferenciaModel {
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "c_transferencia_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "c_cliente_remetente_id", referencedColumnName= "c_cliente_id")
    private ClienteModel clienteRemetente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "c_cliente_destinatario_id", referencedColumnName= "c_cliente_id")
    private ClienteModel clienteDestinatario;

    @Column(name = "c_valor", nullable = false)
    private Double valor;

    @Column(name = "c_created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "c_updated_at")
    private LocalDateTime updated_at;
}
