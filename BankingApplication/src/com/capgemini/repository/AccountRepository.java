package com.capgemini.repository;

import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface AccountRepository {
	
	boolean save(Account account);
	Account searchAccount(int accountNumber) throws InvalidAccountNumberException;

}
