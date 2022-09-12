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
@Table(name = "cliente_t")
public class ClienteModel {
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "c_cliente_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "c_nome", nullable = false)
    private String nome;

    @Column(name = "c_email", nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "c_senha", nullable = false)
    private String senha;

    @Column(name = "c_cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "c_tel")
    private String tel;

    @Column(name = "c_endereco")
    private String endereco;

    @Column(name = "c_created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "c_updated_at")
    private LocalDateTime updated_at;
}
