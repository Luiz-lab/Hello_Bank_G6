package ibm.ibtc.Hello_bank_G6.Data;

import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheUsuarioData implements UserDetails {

    private final Optional<ClienteModel> cliente;

    public DetalheUsuarioData(Optional<ClienteModel> cliente) {
        this.cliente = cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return cliente.orElse(new ClienteModel()).getSenha();
    }

    @Override
    public String getUsername() {
        return cliente.orElse(new ClienteModel()).getCpf();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
