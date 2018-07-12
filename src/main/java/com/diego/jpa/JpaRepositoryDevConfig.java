package com.diego.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class JpaRepositoryDevConfig implements JpaRepositoryConfig {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");

	private EntityManager entityManager = factory.createEntityManager();
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void close() {
		entityManager.close();
		factory.close();
	}
}
