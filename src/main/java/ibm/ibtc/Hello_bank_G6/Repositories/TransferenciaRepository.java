package ibm.ibtc.Hello_bank_G6.Repositories;

import ibm.ibtc.Hello_bank_G6.Models.TransacaoModel;
import ibm.ibtc.Hello_bank_G6.Models.TransferenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<TransferenciaModel, Integer> {

}
