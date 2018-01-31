package com.sep.acquirer.transaction;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.hadoop.mapred.gethistory_jsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sep.acquirer.bankMember.BankMember;
import com.sep.acquirer.bankMember.BankMemberRepository;
import com.sep.acquirer.paymentRequest.PaymentRequest;
import com.sep.acquirer.paymentRequest.PaymentRequestCard;


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
		
		if(this.checkRequestData(paymentRequest)) {
			
			BankMember member = memberRepository.findByCardNumber(paymentRequest.getCardNum());
			double curentAmount = member.getAmount();
			member.setAmount(curentAmount - paymentRequest.getPolicyPrice());
			
			if( memberRepository.save(member) != null)
			{	//Save transaction if member amount is updated
				Transaction transaction = new Transaction();
				transaction.setAmount(paymentRequest.getPolicyPrice());
				transaction.setBank(member.getBank());
				transaction.setBankMember(member);
				repository.save(transaction);
			}
				
				return true;
			
		}
		return false;
	}

	@Override
	public boolean checkRequestData(PaymentRequest paymentRequest) {
		
		BankMember member = memberRepository.findByCardNumber(paymentRequest.getCardNum());
		if(member != null){																		//TODO: DO MORE CHECKING
			if(member.getAmount() >= paymentRequest.getPolicyPrice() 
					&& member.isValid() 
					&& member.getBillNumber() > 0 
					&& paymentRequest.getCvv2().equals(member.getCvv2())) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean submitPayment(PaymentRequestCard paymentRequest) {
		if(this.checkRequestData(paymentRequest)) {
					
			BankMember member = memberRepository.findByBillNumber(Double.parseDouble(paymentRequest.getBillNum()));
			
			double curentAmount = member.getAmount();
			member.setAmount(curentAmount - paymentRequest.getPolicyPrice());
			
			if( memberRepository.save(member) != null)
			{	//Save transaction if member amount is updated
					Transaction transaction = new Transaction();
					transaction.setAmount(paymentRequest.getPolicyPrice());
					transaction.setBank(member.getBank());
					transaction.setBankMember(member);
					repository.save(transaction);
				}
					
					return true;
				
			}
			return false;
}

	@Override
	public boolean checkRequestData(PaymentRequestCard paymentRequest) {
		BankMember member = memberRepository.findByBillNumber(Double.parseDouble(paymentRequest.getBillNum()));
		if(member != null){																		//TODO: DO MORE CHECKING
			if(member.getAmount() >= paymentRequest.getPolicyPrice() 
					&& member.isValid() 
					&& member.getName().equals(paymentRequest.getHolderName()) ) {
				return true;
			}
		}
		
		return false;
	}
	
	

}
