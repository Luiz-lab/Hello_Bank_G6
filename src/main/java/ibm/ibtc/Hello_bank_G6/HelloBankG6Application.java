package ibm.ibtc.Hello_bank_G6;

import ibm.ibtc.Hello_bank_G6.Models.TipoTransacaoEnum;
import ibm.ibtc.Hello_bank_G6.Models.TransacaoModel;
import ibm.ibtc.Hello_bank_G6.Models.TransferenciaModel;
import ibm.ibtc.Hello_bank_G6.Utils.HttpHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Security;

@SpringBootApplication()
public class HelloBankG6Application {

	public static void main(String[] args) {
		SpringApplication.run(HelloBankG6Application.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
