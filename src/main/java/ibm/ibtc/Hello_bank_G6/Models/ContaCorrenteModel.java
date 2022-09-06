package ibm.ibtc.Hello_bank_G6.Models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "conta_corrente_t")
public class ContaCorrenteModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_conta_corrente_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "c_saldo", nullable = false)
    private Double saldo;

    @Column(name = "c_limite_negativo") //if null = 0
    private Double limiteNegativo;

    @Column(name = "c_rentabilidade_at")
    private Double rentabilidade;

    @Column(name = "c_created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "c_updated_at")
    private LocalDateTime updated_at;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "c_cliente_id")
    private ClienteModel clienteModel;
}

