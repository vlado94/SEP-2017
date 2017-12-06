package da.insurancePolicy;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

import da.factor.Factor;
import da.person.Person;

@Entity
public class InsurancePolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate endDate;

	private Integer duration;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Insurance_policies_persons", joinColumns = {
			@JoinColumn(name = "insurance_policy_id") }, inverseJoinColumns = { @JoinColumn(name = "person_id") })
	private Set<Person> persons = new HashSet<Person>();

	@ManyToMany
	private Set<Factor> factors = new HashSet<Factor>();

	public InsurancePolicy() {
		super();
	}

	public InsurancePolicy(LocalDate startDate, LocalDate endDate, Integer duration) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Set<Factor> getFactors() {
		return factors;
	}

	public void setFactors(Set<Factor> factors) {
		this.factors = factors;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

}
