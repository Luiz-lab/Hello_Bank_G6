package ibm.ibtc.Hello_bank_G6.Repositories;

import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;
import ibm.ibtc.Hello_bank_G6.Models.ContaCorrenteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IContaCorrenteRepository extends JpaRepository<ContaCorrenteModel, UUID> {
    Optional<ContaCorrenteModel> findByClienteModel(ClienteModel clienteModel);
}
