package com.sep.acquirer.bankMember;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.dto.BankMemberDTO;
import model.request.InsurancePolicyRequest;
import model.request.PinRequest;

@RestController
@RequestMapping("/bankMember")
public class BankMemberController {

	@Autowired
	BankMemberService bankMemberService;

	@Autowired
	ConversionService conversionService;

	@GetMapping
	public List<BankMemberDTO> findAll() {

		List<BankMember> bankMemberList = bankMemberService.findAll();
		List<BankMemberDTO> dtoList = new ArrayList<BankMemberDTO>();
		for (BankMember bankMember : bankMemberList) {
			if (bankMember.isValid()) {
				BankMemberDTO dto = new BankMemberDTO();
				dto.setId(bankMember.getId());
				dto.setAmount(bankMember.getAmount());
				dto.setBillNumber(bankMember.getBillNumber());
				dto.setCardNumber(bankMember.getCardNumber());
				dto.setName(bankMember.getName());
				dto.setValid(bankMember.isValid());
				dtoList.add(dto);
			}
		}

		return dtoList;
	}

	@PostMapping("/getCardNumber")
	public String getCardNumber(@RequestBody PinRequest obj) {
		String bmb = bankMemberService.findById(obj.getCardHolder()).getCardNumber();
		return bmb;
	}

	@PostMapping("/blockCard")
	public void blockCard(@RequestBody PinRequest obj) {
		BankMember bankMember = bankMemberService.findById(obj.getCardHolder());
		bankMember.setValid(false);
		bankMemberService.save(bankMember);
	}

}
