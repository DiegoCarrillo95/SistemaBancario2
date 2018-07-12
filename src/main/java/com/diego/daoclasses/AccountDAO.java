package com.diego.daoclasses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.diego.models.Account;

@Repository
public class AccountDAO {

	// Account database:
	// id - ClientId - Balance

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	

	public Account getAccountById(int id) {
		List<Account> accounts = jdbcTemplate.query("select * from accounts where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Account>(Account.class));

		if(accounts.size() == 0) {
			return null;
		}
		
		return accounts.get(0);
	}

	public List<Account> getClientAccounts(int clientId) {
		return jdbcTemplate.query("select * from accounts where clientId = ?", new Object[] { clientId },
				new BeanPropertyRowMapper<Account>(Account.class));
	}

	public void insertAccount(Account account) {
		jdbcTemplate.update("INSERT INTO accounts (id, clientId, balance) VALUES (?, ?, ?)", account.getId(),
				account.getClientId(), account.getBalance());
	}

	public void deleteAccount(Account account) {

	}

	public void uptadeAccount(Account account) {
		jdbcTemplate.update("update accounts set balance = ? where id = ? and clientId = ?;",
				new Object[] { account.getBalance(), account.getId(), account.getClientId() });

	}
}
