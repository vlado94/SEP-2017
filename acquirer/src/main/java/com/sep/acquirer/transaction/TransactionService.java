package com.sep.acquirer.transaction;

import java.util.List;

public interface TransactionService {
	
	public List<Transaction> findAll();

	public Transaction findOne(Long id);

	public Transaction save(Transaction transaction);

	public void delete(Long id);

}
