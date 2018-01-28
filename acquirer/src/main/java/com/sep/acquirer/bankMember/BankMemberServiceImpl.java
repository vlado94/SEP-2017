package com.sep.acquirer.bankMember;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankMemberServiceImpl implements BankMemberService{
	
	@Autowired
	BankMemberRepository repository;

	@Override
	public List<BankMember> findAll() {
		return (List<BankMember>) repository.findAll();
	}

	@Override
	public BankMember findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public BankMember save(BankMember bankMember) {
		return repository.save(bankMember);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);		
	}

	@Override
	public BankMember findByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		return repository.findByCardNumber(cardNumber);
	}

}
