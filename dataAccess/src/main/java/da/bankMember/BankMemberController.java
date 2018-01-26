package da.bankMember;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.dto.BankMemberDTO;

@RestController
@RequestMapping("/bankMember")
public class BankMemberController {
	
	@Autowired
	BankMemberService bankMemberService;
	
	@GetMapping
	public List<BankMemberDTO> findAll(){
		
		List<BankMember> bankMemberList = bankMemberService.findAll();
		List<BankMemberDTO> dtoList = new ArrayList<BankMemberDTO>();
		for(BankMember bankMember : bankMemberList) {
			BankMemberDTO dto = new BankMemberDTO();
			dto.setId(bankMember.getId());
			dto.setAmount(bankMember.getAmount());
			dto.setBillNumber(bankMember.getBillNumber());
			dto.setCardNumber(bankMember.getCardNumber());
			dto.setName(bankMember.getName());
			dto.setValid(bankMember.isValid());
			dtoList.add(dto);
		}
		
		return dtoList;
	}

}
