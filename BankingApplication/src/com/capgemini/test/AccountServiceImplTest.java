package com.capgemini.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import com.capgemini.exception.*;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.services.*;

import static org.mockito.Mockito.when;
public class AccountServiceImplTest {

	@Mock
	AccountRepository accountRepository;
	
	AccountService accountService;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*
	 * create account
	 * 1.when the amount is less than 500 system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */
	@Test(expected=com.capgemini.exception.InsufficentInitialAmmountException.class)
	public void whenTheAmountIsLessThanFiveHundredSystemShouldThrowException() throws InsufficentInitialAmmountException
	{
		accountService.createAccount(101, 300);
	}
	
	//accountService.createAccount(101, 1000);
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficentInitialAmmountException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepository.save(account)).thenReturn(true);
		
		assertEquals(account, accountService.createAccount(101, 5000));
		accountService.createAccount(101, 1000);
		
	}
	
	
	
	/*
	 * Deposit Amount in account 
	 * 1.when the Account is invalid system should throw exception
	 * 2.when the valid info is passed amount should be updated in account successfully
	 */
	 
	@Test(expected=com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenTheAccountIsInvalidSystemShouldThrowExceptionForDeposit() throws InvalidAccountNumberException
	{
		accountService.depositAmount(102, 500);
	}
	@Test(expected=com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenTheValidInfoIsPassedamountShouldBeUpdatedInAccountSuccessfullyForDeposit() throws InvalidAccountNumberException, InsufficentInitialAmmountException
	{accountService.createAccount(101, 1000);
		accountService.depositAmount(101, 2500);

	}
	
	/*
	
	 * withdraw Amount From account 
	 * 1.when the Account is invalid system should throw exception
	 * 2.when the valid info is passed amount should be updated in account successfully
	 
	@Test(expected=com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenTheAccountIsInvalidSystemShouldThrowExceptionForWithdraw() throws InsufficiantBalanceException, InvalidAccountNumberException
	{
		accountService.withdrawAmount(104, 500);
	}
	@Test
	public void whenTheValidInfoIsPassedamountShouldBeUpdatedInAccountSuccessfullyForWithdraw() throws InvalidAccountNumberException, InsufficiantBalanceException
	{
		accountService.withdrawAmount(101, 500);

	}
	
	*/
}
