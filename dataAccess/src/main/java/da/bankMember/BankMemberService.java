package da.bankMember;

import java.util.List;

public interface BankMemberService {
	
	public List<BankMember> findAll();
	
	public BankMember findById(Long id);
	
	public BankMember save(BankMember bankMember);
	
	public void delete(Long id);

}
