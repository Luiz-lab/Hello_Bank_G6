package ibm.ibtc.Hello_bank_G6.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "conta_corrente_t")
public class ContaCorrenteModel{
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "c_conta_corrente_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "c_saldo", nullable = false)
    private Double saldo;

    @Column(name = "c_limite_negativo") //if null = 0
    private Double limiteNegativo;

    @Column(name = "c_created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "c_updated_at")
    private LocalDateTime updated_at;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "c_cliente_id", unique = true)
    private ClienteModel clienteModel;

}

