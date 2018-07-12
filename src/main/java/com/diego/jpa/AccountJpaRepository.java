package com.diego.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diego.models.Account;

@Repository
@Transactional
public class AccountJpaRepository {

	// Referência:
	// https://www.caelum.com.br/apostila-java-web/uma-introducao-pratica-ao-jpa-com-hibernate/#trabalhando-com-os-objetos-o-entitymanager

	@Autowired
	JpaRepositoryConfig jpaRepositoryConfig;
	
	EntityManager entityManager;
	
	@PostConstruct
	private void initEntityManager() {
		entityManager = jpaRepositoryConfig.getEntityManager();
	}
	
	public Account getAccountById(int id) {
		return entityManager.find(Account.class, id);
	}

	public List<Account> getClientAccounts(int clientId) {
		
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("Select ac from Account ac where ac.clientId=:clientId");
		query.setParameter("clientId", clientId);
		
		@SuppressWarnings("unchecked")
		List<Account> accounts = query.getResultList();
		
		entityManager.getTransaction().commit();
		
		return accounts;

	}

	public int insertAccount(Account account) {
		entityManager.getTransaction().begin();
		entityManager.persist(account);
		entityManager.flush();
		entityManager.getTransaction().commit();
		return account.getId();
	}

	public void deleteAccount(Account account) {
		entityManager.getTransaction().begin();
		Account accountDelete = entityManager.find(Account.class, account.getId());
		if(accountDelete != null) {
			entityManager.remove(accountDelete);
		}
		entityManager.getTransaction().commit();
	}

	public void uptadeAccount(Account account) {
		entityManager.getTransaction().begin();
		entityManager.merge(account);
		entityManager.getTransaction().commit();
	}
	
}
