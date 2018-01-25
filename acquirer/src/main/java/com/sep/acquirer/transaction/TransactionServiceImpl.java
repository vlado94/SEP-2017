package com.sep.acquirer.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;



public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository repository;
	
	
	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return (List<Transaction>) repository.findAll();
	}

	@Override
	public Transaction findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public Transaction save(Transaction transaction) {
		// TODO Auto-generated method stub
		return repository.save(transaction);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}
