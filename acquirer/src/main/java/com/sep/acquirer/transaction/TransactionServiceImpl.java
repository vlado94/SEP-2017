package com.sep.acquirer.transaction;

import static org.mockito.Matchers.doubleThat;

import java.util.Currency;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sep.acquirer.bank.BankRepository;
import com.sep.acquirer.bankMember.BankMember;
import com.sep.acquirer.bankMember.BankMemberRepository;
import com.sep.acquirer.paymentRequest.PaymentRequest;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private BankMemberRepository memberRepository;
	
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

	@Override
	public boolean submitPayment(PaymentRequest paymentRequest) {
		
		//first 3 are bank code
		String accountNumber = paymentRequest.getCardNum().substring(3);
		
		BankMember member = memberRepository.findByCardNumber(accountNumber);
		if(member != null) {
			double curentAmount = member.getAmount();
			member.setAmount(curentAmount - paymentRequest.getPolicyPrice());
			memberRepository.save(member);
		}
		
		return false;
	}
	
	

}
