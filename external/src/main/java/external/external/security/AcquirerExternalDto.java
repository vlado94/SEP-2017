package external.external.security;

public class AcquirerExternalDto {

	private Long policyID;
	private String hash;
	
	public AcquirerExternalDto() {
		
	}
	
	public AcquirerExternalDto(Long policyID, String hash) {
		super();
		this.policyID = policyID;
		this.hash = hash;
	}
	
	public Long getPolicyID() {
		return policyID;
	}
	public void setPolicyID(Long policyID) {
		this.policyID = policyID;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
}
