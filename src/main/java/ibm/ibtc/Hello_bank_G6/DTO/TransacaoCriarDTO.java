package ibm.ibtc.Hello_bank_G6.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class TransacaoCriarDTO {
    private String tipoTransacao;
    private Double valor;
    private String clienteId;
}
