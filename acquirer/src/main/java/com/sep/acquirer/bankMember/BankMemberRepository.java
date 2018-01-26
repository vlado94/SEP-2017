package com.sep.acquirer.bankMember;


import org.springframework.data.repository.PagingAndSortingRepository;

public interface BankMemberRepository extends PagingAndSortingRepository<BankMember, Long>{

	public BankMember findByCardNumber(String cardNumber);
}
