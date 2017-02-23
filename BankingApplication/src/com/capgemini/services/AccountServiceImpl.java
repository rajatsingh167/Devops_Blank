package com.capgemini.services;

import com.capgemini.exception.InsufficentInitialAmmountException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.exception.InsufficiantBalanceException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	Account account = new Account();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capgemini.services.AccountService#createAccount(int, int)
	 */
	@Override
	public Account createAccount(int accountNumber, int amount)
			throws InsufficentInitialAmmountException {

		if (amount < 500) {
			throw new InsufficentInitialAmmountException();
		}

		account.setAccountNumber(accountNumber);
		account.setAmount(amount);

		if (accountRepository.save(account)) {
			return account;
		}

		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capgemini.services.AccountService#depositAmount(int, int)
	 */
	@Override
	public int depositAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException {
		System.out.println("acont number "+ accountNumber + "amount "+ amount);
		Account accontresult = accountRepository.searchAccount(accountNumber);
		if (accontresult == null) {
			System.out.println("Account Number deposit 1 "+account.getAccountNumber() +"Account Balance"+account.getAmount());
			throw new InvalidAccountNumberException();
		}else{
			accontresult.setAmount(account.getAmount()+amount);
			//accontresult.setAccountNumber(accountNumber);
			accountRepository.save(account);
			System.out.println("Account Number deposit 2"+account.getAccountNumber() +"Account Balance"+account.getAmount());
		}
		return account.getAmount();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capgemini.services.AccountService#withdrawAmount(int, int)
	 */
	@Override
	public int withdrawAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException, InsufficiantBalanceException {
		
		account.setAccountNumber(accountNumber);
		account.setAmount(account.getAmount()-amount);
		
		Account searchAccount = accountRepository.searchAccount(accountNumber);
		if (searchAccount == null) {
			System.out.println("Account Number withdraw"+account.getAccountNumber() +"Account Balance"+account.getAmount());
			throw new InvalidAccountNumberException();
		}else if(searchAccount.getAmount()< amount) {
			System.out.println("Account Number withdraw"+account.getAccountNumber() +"Account Balance"+account.getAmount());
		 throw new InsufficiantBalanceException();
		}else{
			System.out.println("Account Number withdraw"+account.getAccountNumber() +"Account Balance"+account.getAmount());
			accountRepository.save(account);
			return account.getAmount();
		}
		
	}

}
