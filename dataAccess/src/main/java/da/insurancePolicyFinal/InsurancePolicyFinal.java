package da.insurancePolicyFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import da.insurancePolicyPrice.InsurancePolicyPrice;
import da.person.Person;

@Entity
public class InsurancePolicyFinal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate startDate;
	private int durationForTravel;
	private String region;
	private String sport;
	private String amount;
	private String typeOfPolicy;

	/* home */
	@OneToOne(cascade = CascadeType.ALL)
	private InsurancePolicyHomeFinal insurancePolicyHome;

	/* car */
	@OneToOne(cascade = CascadeType.ALL)
	private InsurancePolicyCarFinal insurancePolicyCar;
	private boolean paid;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Insurance_policies_persons", joinColumns = {
			@JoinColumn(name = "insurance_policy_id") }, inverseJoinColumns = { @JoinColumn(name = "person_id") })
	private List<Person> persons = new ArrayList<Person>();
	// private String jmbgOwnerCar;

	@OneToOne(cascade = CascadeType.ALL)
	private InsurancePolicyPrice price = new InsurancePolicyPrice();// first is for travel, second for
																	// car//alway two elements
	private Double totalPrice;

	public InsurancePolicyFinal() {

	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getDurationForTravel() {
		return durationForTravel;
	}

	public void setDurationForTravel(int durationForTravel) {
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

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public InsurancePolicyPrice getPrice() {
		return price;
	}

	public void setPrice(InsurancePolicyPrice price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public InsurancePolicyHomeFinal getInsurancePolicyHome() {
		return insurancePolicyHome;
	}

	public void setInsurancePolicyHome(InsurancePolicyHomeFinal insurancePolicyHome) {
		this.insurancePolicyHome = insurancePolicyHome;
	}

	public InsurancePolicyCarFinal getInsurancePolicyCar() {
		return insurancePolicyCar;
	}

	public void setInsurancePolicyCar(InsurancePolicyCarFinal insurancePolicyCar) {
		this.insurancePolicyCar = insurancePolicyCar;
	}

}
