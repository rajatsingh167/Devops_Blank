package com.capgemini.repository;

import java.util.ArrayList;
import java.util.LinkedList;

import com.capgemini.exception.*;
import com.capgemini.model.Account;

public class AccountRepositoryImp implements AccountRepository {

	LinkedList<Account> accounts = new LinkedList<>();

	@Override
	public boolean save(Account account) {
		System.out.println("from account Search "+new Account());
		accounts.add(account);
		return true;
	}

	
	// search account 
	@Override
	public Account searchAccount(int accountNumber) throws InvalidAccountNumberException {

		System.out.println("from account Search "+new Account());
		for (Account account : accounts) {

			if (account.getAccountNumber() == accountNumber) {
				return account;
			}

		}
		throw new InvalidAccountNumberException();

	}

}
