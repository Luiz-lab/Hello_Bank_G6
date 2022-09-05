package ibm.ibtc.Hello_bank_G6.Repositories;

import ibm.ibtc.Hello_bank_G6.Models.ContaCorrenteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaPoupancaRepository extends JpaRepository<ContaCorrenteModel, Integer> {

}
