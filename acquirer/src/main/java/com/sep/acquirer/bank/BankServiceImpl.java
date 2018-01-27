package com.sep.acquirer.bank;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sep.acquirer.transaction.TransactionRepository;


@Service
@Transactional
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository repository;
	
	@Override
	public List<Bank> findAll() {
		// TODO Auto-generated method stub
		return (List<Bank>) repository.findAll();
	}

	@Override
	public Bank findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public Bank save(Bank bank) {
		// TODO Auto-generated method stub
		return repository.save(bank);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}

	@Override
	public Bank findByCode(String code) {
		return repository.findByCode(code);
	}

}
