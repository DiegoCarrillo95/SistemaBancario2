package integrationtests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.diego.facade.BankFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestsConfiguration.class)
@ActiveProfiles("test")
public class IntegrationTests {

	private final double DELTA = 0.05;

	@Autowired
	private BankFacade bankFacade;
	
	@Autowired 
	private JpaRepositoryTestConfig jpaRepositoryConfig;
	
	@After
	public void clearDatabases() {
		jpaRepositoryConfig.getEntityManager().getTransaction().begin();
		jpaRepositoryConfig.getEntityManager().createQuery("delete from Client cl").executeUpdate();
		jpaRepositoryConfig.getEntityManager().createQuery("delete from Account ac").executeUpdate();
		jpaRepositoryConfig.getEntityManager().getTransaction().commit();
	}

	@Test
	public void withdrawIntegrationTest() {

		// Arrange:
		int clientId = bankFacade.addClient("Diego Carrillo");
		int accountId = bankFacade.addAccount(clientId);
		bankFacade.deposit(clientId, accountId, 100.00);

		// Act:
		bankFacade.withdraw(clientId, accountId, 30.00);

		// Assert:
		assertEquals(70.00, bankFacade.getBalance(clientId, accountId), DELTA);
	}

	@Test
	public void transferIntegrationTest() {

		// Arrange:
		int client1Id = bankFacade.addClient("Diego Carrillo");
		int account1Id = bankFacade.addAccount(client1Id);
		bankFacade.deposit(client1Id, account1Id, 100.00);
		int client2Id = bankFacade.addClient("Jorge Silva");
		int account2Id = bankFacade.addAccount(client2Id);

		// Act:
		bankFacade.transfer(client1Id, account1Id, client2Id, account2Id, 70.00);

		// Assert:
		assertEquals(30.00, bankFacade.getBalance(client1Id, account1Id), DELTA);
		assertEquals(70.00, bankFacade.getBalance(client2Id, account2Id), DELTA);
	}

}
