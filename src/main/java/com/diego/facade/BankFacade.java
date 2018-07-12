package com.diego.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.diego.models.Account;
import com.diego.models.Client;
import com.diego.services.AccountException;
import com.diego.services.AccountServices;
import com.diego.services.ClientException;
import com.diego.services.ClientServices;

@Component
public class BankFacade {

	private static final Logger LOGGER = LoggerFactory.getLogger(BankFacade.class);

	@Autowired
	private ClientServices clientServices;

	@Autowired
	private AccountServices accountServices;

	private List<Client> clients;

	public void printClients() {
		clients = clientServices.getClientList();
		LOGGER.info("{}", "Clients:");
		for (Client client : clients) {
			LOGGER.info("{}", client);
		}
	}

	public int addAccount(int clientId) {
		try {
			return accountServices.createAccount(clientId);
		} catch (AccountException e) {
			LOGGER.info(e.getMessage());
		} catch (ClientException e) {
			LOGGER.info(e.getMessage());
		}
		return -1; 
	}



	public int addClient(String name) {
		try {
			return clientServices.createClient(name);
		} catch (ClientException e) {
			LOGGER.info(e.getMessage());
		}
		
		return - 1;
	}

	public void transfer(int clientFromId, int accountFromId, int clientToId, int accountToId, double value) {
		// TODO: teste de integração
		try {
			Account accountFrom = clientServices.getClientAccount(clientFromId, accountFromId);
			Account accountTo = clientServices.getClientAccount(clientToId, accountToId);

			// TODO: transaction
			accountServices.debitAccount(accountFrom, value);
			accountServices.creditAccount(accountTo, value);

		} catch (ClientException e) {
			LOGGER.info(e.getMessage());

		} catch (AccountException e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void deposit(int clientId, int accountId, double value) {
		// TODO: teste de integração
		try {
			Account account = clientServices.getClientAccount(clientId, accountId);
			accountServices.creditAccount(account, value);
			LOGGER.info("{}", "Deposit succesfully executed");

		} catch (ClientException e) {
			LOGGER.info(e.getMessage());

		} catch (AccountException e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void withdraw(int clientId, int accountId, double value) {
		// TODO: teste de integração
		try {
			Account account = clientServices.getClientAccount(clientId, accountId);
			accountServices.debitAccount(account, value);
			LOGGER.info("{}", "Withdraw succesfully executed");

		} catch (ClientException e) {
			LOGGER.info(e.getMessage());

		} catch (AccountException e) {
			LOGGER.info(e.getMessage());
		}
	}
	
	public double getBalance(int clientId, int accountId) {
		// TODO: teste de integração
		try {
			Account account = clientServices.getClientAccount(clientId, accountId);
			return account.getBalance();

		} catch (ClientException e) {
			LOGGER.info(e.getMessage());

		} catch (AccountException e) {
			LOGGER.info(e.getMessage());
		}
		return -1;
	}

}
