package ibm.ibtc.Hello_bank_G6.Services;

import ibm.ibtc.Hello_bank_G6.Data.DetalheUsuarioData;
import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;
import ibm.ibtc.Hello_bank_G6.Repositories.IClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final IClienteRepository _clienteRepository;

    public DetalheUsuarioServiceImpl(IClienteRepository _clienteRepository) {
        this._clienteRepository = _clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {

        Optional<ClienteModel> cliente = _clienteRepository.findByCpf(cpf);

        if(cliente.isEmpty()){
            throw new UsernameNotFoundException("Usuario com este cpf não encontrado.");
        }

        return new DetalheUsuarioData(cliente);
    }
}
