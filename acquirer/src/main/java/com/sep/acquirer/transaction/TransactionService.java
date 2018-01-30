package com.sep.acquirer.transaction;

import java.util.List;

import com.sep.acquirer.paymentRequest.PaymentRequest;
import com.sep.acquirer.paymentRequest.PaymentRequestCard;

public interface TransactionService {
	
	public List<Transaction> findAll();

	public Transaction findOne(Long id);

	public Transaction save(Transaction transaction);

	public void delete(Long id);
	
	public boolean submitPayment(PaymentRequest paymentRequest);
	
	public boolean checkRequestData(PaymentRequest paymentRequest);
	
	public boolean submitPayment(PaymentRequestCard paymentRequest);
	
	public boolean checkRequestData(PaymentRequestCard paymentRequest);

	
	
}
