package model.response;

import java.time.LocalDate;
import java.util.List;

import model.request.PersonRequest;


public class InsurancePolicyCheckoutResponse {
  /*travel*/
	private LocalDate startDate;
	private Integer durationForTravel;
	private String region;
	private String sport;
	private String amount;
	private String typeOfPolicy;
	private List<PersonRequest> persons;
	private InsurancePolicyCalculatePriceResponse priceAndDiscountsForTravel;
	
	/*home*/
	private Integer durationForHome;
	private String risk;
	private String value;
	private String age;
	private String size;
	private String address;
	private String firstNameOwnerHome;
	private String lastNameOwnerHome;
	//private String jmbgOwnerHome;
	private Double priceForHome;
	
	/*car*/
	private Integer durationForCar;
	private String slepovanje;
	private String popravka;
	private String prevoz;
	private String smestaj;
	private String typeOfVehicle;
	private Integer year;
	private String registrationNumber;
	private String chassisNumber;
	private String firstNameOwnerCar;
	private String lastNameOwnerCar;
	//private String jmbgOwnerCar;
	private InsurancePolicyCalculatePriceResponse priceAndDiscountsForCar;
	
	
	private Double totalPrice;

	public InsurancePolicyCheckoutResponse() {
		
	}
	
	
	public InsurancePolicyCheckoutResponse(LocalDate startDate, Integer durationForTravel, String region, String sport,
			String amount, String typeOfPolicy, List<PersonRequest> persons,
			InsurancePolicyCalculatePriceResponse priceAndDiscountsForTravel, Integer durationForHome, String risk,
			String value, String age, String size, String address, String firstNameOwnerHome, String lastNameOwnerHome,
			Double priceForHome, Integer durationForCar, String slepovanje, String popravka, String prevoz,
			String smestaj, String typeOfVehicle, Integer year, String registrationNumber, String chassisNumber,
			String firstNameOwnerCar, String lastNameOwnerCar,
			InsurancePolicyCalculatePriceResponse priceAndDiscountsForCar, Double totalPrice) {
		super();
		this.startDate = startDate;
		this.durationForTravel = durationForTravel;
		this.region = region;
		this.sport = sport;
		this.amount = amount;
		this.typeOfPolicy = typeOfPolicy;
		this.persons = persons;
		this.priceAndDiscountsForTravel = priceAndDiscountsForTravel;
		this.durationForHome = durationForHome;
		this.risk = risk;
		this.value = value;
		this.age = age;
		this.size = size;
		this.address = address;
		this.firstNameOwnerHome = firstNameOwnerHome;
		this.lastNameOwnerHome = lastNameOwnerHome;
		this.priceForHome = priceForHome;
		this.durationForCar = durationForCar;
		this.slepovanje = slepovanje;
		this.popravka = popravka;
		this.prevoz = prevoz;
		this.smestaj = smestaj;
		this.typeOfVehicle = typeOfVehicle;
		this.year = year;
		this.registrationNumber = registrationNumber;
		this.chassisNumber = chassisNumber;
		this.firstNameOwnerCar = firstNameOwnerCar;
		this.lastNameOwnerCar = lastNameOwnerCar;
		this.priceAndDiscountsForCar = priceAndDiscountsForCar;
		this.totalPrice = totalPrice;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public Integer getDurationForTravel() {
		return durationForTravel;
	}


	public void setDurationForTravel(Integer durationForTravel) {
		this.durationForTravel = durationForTravel;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getSport() {
		return sport;
	}


	public void setSport(String sport) {
		this.sport = sport;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getTypeOfPolicy() {
		return typeOfPolicy;
	}


	public void setTypeOfPolicy(String typeOfPolicy) {
		this.typeOfPolicy = typeOfPolicy;
	}


	public List<PersonRequest> getPersons() {
		return persons;
	}


	public void setPersons(List<PersonRequest> persons) {
		this.persons = persons;
	}


	public InsurancePolicyCalculatePriceResponse getPriceAndDiscountsForTravel() {
		return priceAndDiscountsForTravel;
	}


	public void setPriceAndDiscountsForTravel(InsurancePolicyCalculatePriceResponse priceAndDiscountsForTravel) {
		this.priceAndDiscountsForTravel = priceAndDiscountsForTravel;
	}


	public Integer getDurationForHome() {
		return durationForHome;
	}


	public void setDurationForHome(Integer durationForHome) {
		this.durationForHome = durationForHome;
	}


	public String getRisk() {
		return risk;
	}


	public void setRisk(String risk) {
		this.risk = risk;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getFirstNameOwnerHome() {
		return firstNameOwnerHome;
	}


	public void setFirstNameOwnerHome(String firstNameOwnerHome) {
		this.firstNameOwnerHome = firstNameOwnerHome;
	}


	public String getLastNameOwnerHome() {
		return lastNameOwnerHome;
	}


	public void setLastNameOwnerHome(String lastNameOwnerHome) {
		this.lastNameOwnerHome = lastNameOwnerHome;
	}


	public Double getPriceForHome() {
		return priceForHome;
	}


	public void setPriceForHome(Double priceForHome) {
		this.priceForHome = priceForHome;
	}


	public Integer getDurationForCar() {
		return durationForCar;
	}


	public void setDurationForCar(Integer durationForCar) {
		this.durationForCar = durationForCar;
	}


	public String getSlepovanje() {
		return slepovanje;
	}


	public void setSlepovanje(String slepovanje) {
		this.slepovanje = slepovanje;
	}


	public String getPopravka() {
		return popravka;
	}


	public void setPopravka(String popravka) {
		this.popravka = popravka;
	}


	public String getPrevoz() {
		return prevoz;
	}


	public void setPrevoz(String prevoz) {
		this.prevoz = prevoz;
	}


	public String getSmestaj() {
		return smestaj;
	}


	public void setSmestaj(String smestaj) {
		this.smestaj = smestaj;
	}


	public String getTypeOfVehicle() {
		return typeOfVehicle;
	}


	public void setTypeOfVehicle(String typeOfVehicle) {
		this.typeOfVehicle = typeOfVehicle;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public String getRegistrationNumber() {
		return registrationNumber;
	}


	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	public String getChassisNumber() {
		return chassisNumber;
	}


	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}


	public String getFirstNameOwnerCar() {
		return firstNameOwnerCar;
	}


	public void setFirstNameOwnerCar(String firstNameOwnerCar) {
		this.firstNameOwnerCar = firstNameOwnerCar;
	}


	public String getLastNameOwnerCar() {
		return lastNameOwnerCar;
	}


	public void setLastNameOwnerCar(String lastNameOwnerCar) {
		this.lastNameOwnerCar = lastNameOwnerCar;
	}


	public InsurancePolicyCalculatePriceResponse getPriceAndDiscountsForCar() {
		return priceAndDiscountsForCar;
	}


	public void setPriceAndDiscountsForCar(InsurancePolicyCalculatePriceResponse priceAndDiscountsForCar) {
		this.priceAndDiscountsForCar = priceAndDiscountsForCar;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
