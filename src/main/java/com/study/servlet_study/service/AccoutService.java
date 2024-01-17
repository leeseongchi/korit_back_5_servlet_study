package com.study.servlet_study.service;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.repository.AccountRepository;

public class AccoutService {
	
	private static AccoutService instance;
	private AccountRepository accountRepository;
	
	private AccoutService() {
		accountRepository = AccountRepository.getInstance(); // 싱글톤으로 생성이 가능해서 결합도가 높은게 아니다.
	}
	
	public static AccoutService getInstance() {
		if(instance == null) {
			instance = new AccoutService();
		}
		return instance;
	}
	
	public int addAcount(Account account) {
		
		return accountRepository.saveAccount(account);
	}
	
	public Account getAccount(String username) {
		return accountRepository.findAccountByUsername(username);
	}
}
