package model.request;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class InsurancePolicyResponse {

	private Long policyID;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;
	private int duration;
	private Long region;
	private Long sport;
	private Long coverAmount;
	private Double amount;
	private List<PersonResponse> persons;

	
	public InsurancePolicyResponse() {
		
	}
	
	public InsurancePolicyResponse(Long policyID, LocalDate startDate, int duration, Long region, Long sport,
			Long coverAmount, Double amount, List<PersonResponse> persons) {
		super();
		this.policyID = policyID;
		this.startDate = startDate;
		this.duration = duration;
		this.region = region;
		this.sport = sport;
		this.coverAmount = coverAmount;
		this.amount = amount;
		this.persons = persons;
	}

	public Long getPolicyID() {
		return policyID;
	}

	public void setPolicyID(Long policyID) {
		this.policyID = policyID;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Long getRegion() {
		return region;
	}

	public void setRegion(Long region) {
		this.region = region;
	}

	public Long getSport() {
		return sport;
	}

	public void setSport(Long sport) {
		this.sport = sport;
	}

	public Long getCoverAmount() {
		return coverAmount;
	}

	public void setCoverAmount(Long coverAmount) {
		this.coverAmount = coverAmount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<PersonResponse> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonResponse> persons) {
		this.persons = persons;
	}
	
	

}
