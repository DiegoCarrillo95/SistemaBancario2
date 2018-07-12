package integrationtests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.diego.jpa.JpaRepositoryConfig;

@Component
@Profile("test")
public class JpaRepositoryTestConfig implements JpaRepositoryConfig{
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("h2");

	private EntityManager entityManager = factory.createEntityManager();
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void close() {
		entityManager.close();
		factory.close();
	}
}
