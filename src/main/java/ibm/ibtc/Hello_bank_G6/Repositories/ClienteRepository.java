package ibm.ibtc.Hello_bank_G6.Repositories;

import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

}
