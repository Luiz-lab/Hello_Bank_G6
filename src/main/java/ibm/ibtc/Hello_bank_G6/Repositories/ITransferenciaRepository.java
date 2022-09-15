package ibm.ibtc.Hello_bank_G6.Repositories;

import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;
import ibm.ibtc.Hello_bank_G6.Models.ContaCorrenteModel;
import ibm.ibtc.Hello_bank_G6.Models.TransferenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ITransferenciaRepository extends JpaRepository<TransferenciaModel, UUID> {
    Optional<TransferenciaModel> findAllByClienteRemetente(ClienteModel clienteModel);
    Optional<TransferenciaModel> findAllByClienteDestinatario(ClienteModel clienteModel);
}
