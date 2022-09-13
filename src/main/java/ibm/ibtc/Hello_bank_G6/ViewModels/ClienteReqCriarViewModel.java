package ibm.ibtc.Hello_bank_G6.ViewModels;

import lombok.Data;

@Data
public class ClienteReqCriarViewModel {
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String tel;
    private String endereco;
}
