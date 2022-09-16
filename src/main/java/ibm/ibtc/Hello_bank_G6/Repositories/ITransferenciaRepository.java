package ibm.ibtc.Hello_bank_G6.Repositories;

import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;
import ibm.ibtc.Hello_bank_G6.Models.ContaCorrenteModel;
import ibm.ibtc.Hello_bank_G6.Models.TransferenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITransferenciaRepository extends JpaRepository<TransferenciaModel, UUID> {
    Optional<List<TransferenciaModel>> findAllByClienteRemetente(ClienteModel clienteModel);
    Optional<List<TransferenciaModel>> findAllByClienteDestinatario(ClienteModel clienteModel);
}
