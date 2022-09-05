package ibm.ibtc.Hello_bank_G6.Repositories;

import ibm.ibtc.Hello_bank_G6.Models.TransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<TransacaoModel, Integer> {

}
