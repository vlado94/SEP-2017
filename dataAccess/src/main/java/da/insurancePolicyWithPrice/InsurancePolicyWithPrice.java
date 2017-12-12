package da.insurancePolicyWithPrice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import da.insurancePolicy.InsurancePolicy;
import model.request.PersonRequest;

//@Entity
public class InsurancePolicyWithPrice {

	private InsurancePolicy insurancePolicy; 
	private List<Double> pricePerPerson;

	public InsurancePolicyWithPrice() {
		super();
	}

	public InsurancePolicyWithPrice(InsurancePolicy policy) {
		super();
		this.insurancePolicy = policy;
		this.pricePerPerson = new ArrayList<Double>();
	}
	public InsurancePolicyWithPrice(InsurancePolicy insurancePolicy,  List<Double> pricePerPerson) {
		super();
		this.insurancePolicy = insurancePolicy;
		this.pricePerPerson = pricePerPerson; 
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
}
