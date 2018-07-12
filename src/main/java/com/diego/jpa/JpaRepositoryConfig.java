package com.diego.jpa;

import javax.persistence.EntityManager;

public interface JpaRepositoryConfig {
	
	public EntityManager getEntityManager();
	
	public void close();
}
