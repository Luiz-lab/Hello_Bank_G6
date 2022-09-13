package ibm.ibtc.Hello_bank_G6.Repositories;

import ibm.ibtc.Hello_bank_G6.Models.TransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITransacaoRepository extends JpaRepository<TransacaoModel, UUID> {

}
