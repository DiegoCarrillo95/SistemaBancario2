package com.diego.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.diego.jpa.AccountJpaRepository;
import com.diego.jpa.ClientJpaRepository;
import com.diego.models.Account;

@Component
public class AccountServices {

	@Autowired
	AccountJpaRepository accountJpaRepository;
	
	@Autowired
	ClientJpaRepository clientJpaRepository;

	public int createAccount(int clientId) {
		
		if(clientJpaRepository.getClientById(clientId) == null) {
			throw new ClientException("Client does not exist");
		}
		
			Account account = new Account();
			account.setBalance(0);
			account.setClientId(clientId);
			return accountJpaRepository.insertAccount(account);
	}
	
	public void debitAccount(Account account, double value) {

		if (account == null) {
			throw new AccountException("Account not found");
		}
		
		if (account.getBalance() < value) {
			throw new AccountException("Not sufficient funds");
		}
		
		account.setBalance(account.getBalance() - value);
		
		accountJpaRepository.uptadeAccount(account);
	}
	
	public void creditAccount(Account account, double value){
		
		if(account == null) {
			throw new AccountException("Account not found");
		}
		
		account.setBalance(account.getBalance() + value);
		
		accountJpaRepository.uptadeAccount(account);
	}
}
