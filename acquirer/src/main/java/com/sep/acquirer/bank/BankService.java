package com.sep.acquirer.bank;

import java.util.List;

import org.springframework.stereotype.Service;


public interface BankService {

	public List<Bank> findAll();

	public Bank findOne(Long id);

	public Bank save(Bank bank);

	public void delete(Long id);
	
	Bank findByCode(String code);
	
}
