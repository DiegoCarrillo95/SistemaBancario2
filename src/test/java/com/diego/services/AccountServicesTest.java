package com.diego.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.diego.jpa.AccountJpaRepository;
import com.diego.models.Account;

@RunWith(MockitoJUnitRunner.class)
public class AccountServicesTest {

	private static final double DELTA = 0.005;
	
	@InjectMocks
	private AccountServices accountServices;
	
	@Mock
	AccountJpaRepository accountJpaRepository;

	@Test
	public void createAccountTest() {
		// Arrange
		when(accountJpaRepository.getAccountById(1)).thenReturn(null);

		// Act
		accountServices.createAccount(1);

		// Assert
		assertTrue(true);
	}

	@Test
	public void createAccountTest_AccountAlreadyExists() {
		
		Account account1 = new Account();
		when(accountJpaRepository.getAccountById(1)).thenReturn(account1);

		try {
			accountServices.createAccount(1);
			fail();
			
		} catch (AccountException e) {
			
			String message = "Account already exists";
			assertEquals(message, e.getMessage());
			
		}

	}
	
	@Test
	public void debitAccountTest() {
		// Arrange
		Account account1 = new Account();
		account1.setBalance(100);
		account1.setId(1);
		when(accountJpaRepository.getAccountById(1)).thenReturn(account1);

		// Act
		accountServices.debitAccount(account1, 50.00);

		// Assert
		assertEquals(50.00, account1.getBalance(), DELTA);
	}

	@Test
	public void debitAccounTest_NotEnoughBalance() {
		// Arrange
		Account account1 = new Account();
		account1.setBalance(50);
		account1.setId(1);
		when(accountJpaRepository.getAccountById(1)).thenReturn(account1);

		// Act
		try {
			accountServices.debitAccount(account1, 100.00);
			fail();
			
		} catch (AccountException e) {
			// Assert
			String message = "Not sufficient funds";
			assertEquals(message, e.getMessage());

		}
	}

	@Test
	public void debitAccounTest_AccountDoesntExist() {
		// Arrange

		// Act
		try {
			accountServices.debitAccount(null, 100.00);
			fail();
			
		} catch (AccountException e) {
			// Assert
			String message = "Account not found";
			assertEquals(message, e.getMessage());
			
		}
	}
	
	@Test
	public void creditAccountTest() {
		// Arrange
		Account account1 = new Account();	
		account1.setBalance(0);
		account1.setId(1);
		when(accountJpaRepository.getAccountById(1)).thenReturn(account1);

		// Act
		accountServices.creditAccount(account1, 100.00);

		// Assert
		assertEquals(100.00, account1.getBalance(), DELTA);
	}

	@Test
	public void creditAccountTest_AccountDoesntExist() {
		// Arrange

		// Act
		try {
			accountServices.creditAccount(null, 100.00);
			fail();
			
		} catch (AccountException e) {
			//Assert
			String message = "Account not found";
			assertEquals(message, e.getMessage());

		}

	}

}
