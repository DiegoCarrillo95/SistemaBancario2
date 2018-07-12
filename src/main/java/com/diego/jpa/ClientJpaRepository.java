package com.diego.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diego.models.Client;

@Repository
@Transactional
public class ClientJpaRepository {

	@Autowired
	JpaRepositoryConfig jpaRepositoryConfig;
	
	EntityManager entityManager;
	
	@PostConstruct
	private void initEntityManager() {
		entityManager = jpaRepositoryConfig.getEntityManager();
	}
	public int insertClient(Client client) {
		entityManager.getTransaction().begin();
		entityManager.persist(client);
		entityManager.flush();
		entityManager.getTransaction().commit();
		return client.getId();
	}

	public void deleteClient(Client client) {

	}

	public void uptadeClient(Client client) {

	}

	@SuppressWarnings("unchecked")
	public List<Client> getClients() {
		return entityManager.createQuery("SELECT cl FROM Client cl").getResultList();
	}

	public Client getClientById(int id) {
		return entityManager.find(Client.class, id);
	}

}
