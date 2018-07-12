package integrationtests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.diego.facade.BankFacade;
import com.diego.jpa.AccountJpaRepository;
import com.diego.jpa.ClientJpaRepository;
import com.diego.jpa.JpaRepositoryConfig;
import com.diego.services.AccountServices;
import com.diego.services.ClientServices;

@Configuration
@Profile("test")
public class IntegrationTestsConfiguration {

	@Bean
	public BankFacade bankfacade() {
		return new BankFacade();
	}
	
	@Bean
	public ClientServices clientServices() {
		return new ClientServices();
	}
	
	@Bean
	public AccountServices accountServices() {
		return new AccountServices();
	}
	
	@Bean
	public AccountJpaRepository accountJpaRepository() {
		return new AccountJpaRepository();
	}
	
	@Bean
	public ClientJpaRepository clientJpaRepository() {
		return new ClientJpaRepository();
	}
	
	@Bean
	public JpaRepositoryConfig jpaRepositoryConfiglate() {
		return new JpaRepositoryTestConfig();
	}
}
