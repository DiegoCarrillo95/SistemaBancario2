package com.diego.sistema_bancario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.diego.facade.BankFacade;
import com.diego.jpa.JpaRepositoryDevConfig;

@Component
public class App {

	@Autowired
	private BankFacade bankFacade;
	
	@Autowired
	JpaRepositoryDevConfig jpaRepositoryConfig;
	

	private static ApplicationContext context;

	private void runBank() {
		bankFacade.addClient("Diego Carrillo");
		//bankFacade.addClient("Jorge Silva");
		bankFacade.printClients();
		
		jpaRepositoryConfig.close();
	}

	public static void main(String[] args) {
		// Carrega contexto diretamente:
		context = new AnnotationConfigApplicationContext(AppConfig.class);

		App p = context.getBean(App.class);
		p.runBank();
	}
}
