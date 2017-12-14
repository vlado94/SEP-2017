package da.insurancePolicyWithPrice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import da.insurancePolicy.InsurancePolicy;
import da.person.Person;
import model.request.PersonRequest;

//@Entity
public class InsurancePolicyWithPrice {

	private InsurancePolicy insurancePolicy;  //u sebi ima Set<Person> persons
	private List<Double> pricePerPerson; 
	private Double amount;

	public InsurancePolicyWithPrice() {
		super();
	}

	public InsurancePolicyWithPrice(InsurancePolicy policy) {
		super();
		this.insurancePolicy = policy;
		this.pricePerPerson = new ArrayList<Double>();
	}
	public InsurancePolicyWithPrice(InsurancePolicy insurancePolicy,  List<Double> pricePerPerson, Double amount) {
		super();
		this.insurancePolicy = insurancePolicy;
		this.pricePerPerson = pricePerPerson; 
		this.amount = amount;
	}

	public InsurancePolicy getInsurancePolicy() {
		return insurancePolicy;
	}

	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	public List<Double> getPricePerPerson() {
		return pricePerPerson;
	}

	public void setPricePerPerson(List<Double> pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
