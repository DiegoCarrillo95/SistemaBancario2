package com.diego.daoclasses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.diego.models.Client;

@Repository
public class ClientDAO {

	// Client database:
	// id - nome

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insertClient(Client client) {
		jdbcTemplate.update("INSERT INTO clients (id, name) VALUES (?, ?)", client.getId(),
				client.getName());
	}

	public void deleteClient(Client client) {

	}

	public void uptadeClient(Client client) {

	}

	public List<Client> getClients() {
		return jdbcTemplate.query("select * from clients", new BeanPropertyRowMapper<Client>(Client.class));
	}
	
	public Client getClientById(int id) {
		List<Client> accounts = jdbcTemplate.query("select * from clients where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Client>(Client.class));
		if(accounts.size() == 0) {
			return null;
		}
		
		return accounts.get(0);
		
	}

}
