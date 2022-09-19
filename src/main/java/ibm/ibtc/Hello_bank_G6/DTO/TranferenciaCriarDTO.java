package ibm.ibtc.Hello_bank_G6.DTO;

import lombok.Data;

@Data
public class TranferenciaCriarDTO {
    private Double valor;
    private String remetenteCpf;
    private String destinatarioCpf;
}
