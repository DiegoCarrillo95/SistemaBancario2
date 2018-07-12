package com.diego.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.diego.jpa.AccountJpaRepository;
import com.diego.jpa.ClientJpaRepository;
import com.diego.models.Account;
import com.diego.models.Client;

@Component
public class ClientServices {

	@Autowired
	ClientJpaRepository clientJpaRepository;

	@Autowired
	AccountJpaRepository accountRepository;

	public List<Client> getClientList() {
		// pega lista de clientes do DAO
		// seta as contas dos clientes da lista
		return clientJpaRepository.getClients();
	}

	public Account getClientAccount(int clientId, int accountId) {
		// if(clientDao.getClientById(clientId) == null) {
		// throw new ClientException("Client not found");
		// }

		List<Account> accounts = accountRepository.getClientAccounts(clientId);

		for (Account account : accounts) {
			if (account.getId() == accountId) {
				return account;
			}
		}

		throw new AccountException("Account not found for this client");
	}

	public int createClient(String name) {
		Client client = new Client();
		client.setName(name);
		
		return clientJpaRepository.insertClient(client);

	}

}
