package com.sep.acquirer.transaction;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface TransactionRepository  extends PagingAndSortingRepository<Transaction, Long> {

}
