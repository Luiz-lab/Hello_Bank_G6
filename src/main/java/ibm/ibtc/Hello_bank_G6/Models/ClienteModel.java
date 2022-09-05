package ibm.ibtc.Hello_bank_G6.Models;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "cliente_t")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_cliente_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "c_nome", nullable = false)
    private String nome;

    @Column(name = "c_email", nullable = false)
    private String email;

    @Column(name = "c_senha", nullable = false)
    private String senha;

    @Column(name = "c_tel")
    private String tel;

    @Column(name = "c_endereco")
    private String endereco;

    @Column(name = "c_created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "c_updated_at")
    private LocalDateTime updated_at;
}
