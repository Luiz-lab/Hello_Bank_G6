package ibm.ibtc.Hello_bank_G6.Repositories;

import ibm.ibtc.Hello_bank_G6.Models.TransferenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITransferenciaRepository extends JpaRepository<TransferenciaModel, UUID> {

}
