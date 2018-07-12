package com.diego.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.diego.jpa.AccountJpaRepository;
import com.diego.models.Account;

@RunWith(MockitoJUnitRunner.class)
public class ClientServicesTest {

	@InjectMocks
	private ClientServices clientServices;

	@InjectMocks
	private AccountServices accountServices;

	@Mock
	private AccountJpaRepository accountJpaRepository;

	@Test
	public void getClientAccountTest() {
		// Arrange
		List<Account> accounts = new ArrayList<>();
		Account account1 = new Account(1, 1);
		Account account2 = new Account(2, 1);
		accounts.add(account1);
		accounts.add(account2);
		when(accountJpaRepository.getClientAccounts(1)).thenReturn(accounts);

		// Act
		Account account = clientServices.getClientAccount(1, 2);

		// Assert
		assertNotNull(account);
	}

	@Test
	public void getClientAccountTest_AccountDoesntExist() {
		// Arrange
		when(accountJpaRepository.getClientAccounts(1)).thenReturn(new ArrayList<>());

		// Act
		try {
			clientServices.getClientAccount(1, 1);
			fail();

		} catch (AccountException e) {
			// Assert
			String message = "Account not found for this client";
			assertEquals(message, e.getMessage());

		}
	}

	@Test
	public void getClientAccountTest_ClientDoesntExist() {
		// Arrange
		when(accountJpaRepository.getClientAccounts(1)).thenReturn(null);

		// Act
		try {
			clientServices.getClientAccount(1, 1);
			fail();

		} catch (ClientException e) {
			// Assert
			String message = "Client not found";
			assertEquals(message, e.getMessage());

		}
	}

}
