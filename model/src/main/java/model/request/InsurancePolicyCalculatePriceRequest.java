package model.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class InsurancePolicyCalculatePriceRequest {
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;
	private int duration;
	private Long region;
	private Long sport;
	private Long amount;
    private String typeOfPolicy;
    private int firstAgeCategory;
    private int secondAgeCategory;
    private int thirdAgeCategory;
	private int numberOfPersons;
	
	public InsurancePolicyCalculatePriceRequest() {
		super();
	}

	public InsurancePolicyCalculatePriceRequest(LocalDate startDate, int duration, Long region, Long sport, Long amount,
			String typeOfPolicy, int firstAgeCategory, int secondAgeCategory,
			int thirdAgeCategory, int numberOfPersons) {
		super();
		this.startDate = startDate;
		this.duration = duration;
		this.region = region;
		this.sport = sport;
		this.amount = amount;
		this.typeOfPolicy = typeOfPolicy;
		this.firstAgeCategory = firstAgeCategory;
		this.secondAgeCategory = secondAgeCategory;
		this.thirdAgeCategory = thirdAgeCategory;
		this.numberOfPersons = numberOfPersons;
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
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getTypeOfPolicy() {
		return typeOfPolicy;
	}

	public void setTypeOfPolicy(String typeOfPolicy) {
		this.typeOfPolicy = typeOfPolicy;
	}

	public int getFirstAgeCategory() {
		return firstAgeCategory;
	}

	public void setFirstAgeCategory(int firstAgeCategory) {
		this.firstAgeCategory = firstAgeCategory;
	}

	public int getSecondAgeCategory() {
		return secondAgeCategory;
	}

	public void setSecondAgeCategory(int secondAgeCategory) {
		this.secondAgeCategory = secondAgeCategory;
	}

	public int getThirdAgeCategory() {
		return thirdAgeCategory;
	}

	public void setThirdAgeCategory(int thirdAgeCategory) {
		this.thirdAgeCategory = thirdAgeCategory;
	}

	public int getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	
	
}
