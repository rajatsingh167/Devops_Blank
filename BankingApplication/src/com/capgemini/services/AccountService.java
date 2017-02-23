package com.capgemini.services;

import com.capgemini.exception.InsufficentInitialAmmountException;
import com.capgemini.exception.InsufficiantBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface AccountService {

	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	public abstract Account createAccount(int accountNumber, int amount)
			throws InsufficentInitialAmmountException;

	public abstract int depositAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException;

	public abstract int withdrawAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException, InsufficiantBalanceException;

}