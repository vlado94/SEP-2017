package da.person;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import da.insurancePolicy.InsurancePolicy;
import da.priceListItem.PriceListItem;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String firstName;
	private String lastName;
	@Length(max = 13, min = 13)
	private String jmbg;
	private String passportNumber;
	private String address;
	private String phone;
	private Boolean contractor;
	private String email;

	@ManyToOne
	private PriceListItem age;

	@ManyToMany(mappedBy="persons")
	private Set<InsurancePolicy> policies = new HashSet<InsurancePolicy>();
	
	public Person() {
		super();
	}

	public Person(Long id, @NotBlank String firstName, String lastName, @Length(max = 13, min = 13) String jmbg,
			String passportNumber, String address, String phone, Boolean contractor, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.passportNumber = passportNumber;
		this.address = address;
		this.phone = phone;
		this.contractor = contractor;
		this.email = email;
	}

	public Person( @NotBlank String firstName, String lastName, @Length(max = 13, min = 13) String jmbg,
			String passportNumber, String address, String phone, Boolean contractor, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.passportNumber = passportNumber;
		this.address = address;
		this.phone = phone;
		this.contractor = contractor;
		this.email = email;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getContractor() {
		return contractor;
	}

	public void setContractor(Boolean contractor) {
		this.contractor = contractor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PriceListItem getAge() {
		return age;
	}

	public void setAge(PriceListItem age) {
		this.age = age;
	}

	public Set<InsurancePolicy> getPolicies() {
		return policies;
	}

	public void setPolicies(Set<InsurancePolicy> policies) {
		this.policies = policies;
	}
	
	

}
