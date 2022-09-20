package ibm.ibtc.Hello_bank_G6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ibm.ibtc.Hello_bank_G6.Models.ClienteModel;

@SpringBootTest
class ClienteTest {

	@Test
	void validarPropriedades() {
		var cliente = new ClienteModel();

		cliente.setNome("");
		cliente.setEmail("");
		cliente.setSenha("");
		cliente.setCpf("");
		cliente.setTel("");
		cliente.setEndereco("");

		assertEquals("", cliente.getNome());
		assertEquals("", cliente.getEmail());
		assertEquals("", cliente.getSenha());
		assertEquals("", cliente.getCpf());
		assertEquals("", cliente.getTel());
		assertEquals("", cliente.getEndereco());


	}

}
