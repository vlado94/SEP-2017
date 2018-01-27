package com.sep.acquirer.bank;

import org.springframework.data.repository.PagingAndSortingRepository;
import java.lang.String;
import com.sep.acquirer.bank.Bank;


public interface BankRepository extends PagingAndSortingRepository<Bank, Long>  {

	Bank findByCode(String code);
}
