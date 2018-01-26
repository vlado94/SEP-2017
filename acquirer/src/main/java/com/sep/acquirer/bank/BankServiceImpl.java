package com.sep.acquirer.bank;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


@Service
@Transactional
public class BankServiceImpl implements BankService {

	@Override
	public List<Bank> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bank findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bank save(Bank bank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bank findByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
