package da.insurancePolicyFinal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InsurancePolicyHomeFinal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer durationForHome;
	private String risk;
	private String value;
	private String age;
	private String size;
	private String address;
	private String firstNameOwnerHome;
	private String lastNameOwnerHome;
	private String jmbgOwnerHome;
	private Double priceForHome;

	public InsurancePolicyHomeFinal() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getJmbgOwnerHome() {
		return jmbgOwnerHome;
	}

	public void setJmbgOwnerHome(String jmbgOwnerHome) {
		this.jmbgOwnerHome = jmbgOwnerHome;
	}

	
}
