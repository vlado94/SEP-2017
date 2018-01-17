package model.request;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class InsurancePolicyRequest {
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate endDate;
	private int duration;
	private Long region;
	private Long sport;
	private Long amount;
	private Long typeOfPolicy;
	private List<PersonRequest> persons;
	private int priceSum;
	Integer firstAgeCategory;
	Integer secondAgeCategory;
	Integer thirdAgeCategory;
	

	public InsurancePolicyRequest() {
		super();
	}

	public InsurancePolicyRequest(LocalDate startDate, LocalDate endDate, int duration, Long region, Long sport,
			Long amount, List<PersonRequest> persons) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
		this.region = region;
		this.sport = sport;
		this.amount = amount;
		this.persons = persons;
	}

	public Long getSport() {
		return sport;
	}

	public void setSport(Long sport) {
		this.sport = sport;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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

	public List<PersonRequest> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonRequest> persons) {
		this.persons = persons;
	}

	public int getPriceSum() {
		return priceSum;
	}

	public void setPriceSum(int priceSum) {
		this.priceSum = priceSum;
	}

	public Integer getFirstAgeCategory() {
		return firstAgeCategory;
	}

	public void setFirstAgeCategory(Integer firstAgeCategory) {
		this.firstAgeCategory = firstAgeCategory;
	}

	public Integer getSecondAgeCategory() {
		return secondAgeCategory;
	}

	public void setSecondAgeCategory(Integer secondAgeCategory) {
		this.secondAgeCategory = secondAgeCategory;
	}

	public Integer getThirdAgeCategory() {
		return thirdAgeCategory;
	}

	public void setThirdAgeCategory(Integer thirdAgeCategory) {
		this.thirdAgeCategory = thirdAgeCategory;
	}

	public Long getTypeOfPolicy() {
		return typeOfPolicy;
	}

	public void setTypeOfPolicy(Long typeOfPolicy) {
		this.typeOfPolicy = typeOfPolicy;
	}

	@Override
	public String toString() {
		return "InsurancePolicyRequest [startDate=" + startDate + ", duration=" + duration + ", region=" + region
				+ ", sport=" + sport + ", amount=" + amount + ", typeOfPolicy=" + typeOfPolicy + ", priceSum="
				+ priceSum + ", firstAgeCategory=" + firstAgeCategory + ", secondAgeCategory=" + secondAgeCategory
				+ ", thirdAgeCategory=" + thirdAgeCategory + "]";
	}
	

}
