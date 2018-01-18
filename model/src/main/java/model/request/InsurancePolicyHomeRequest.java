package model.request;

public class InsurancePolicyHomeRequest {

	private Integer duration;
	private Long risk;
	private Long value;
	private Long age;
	private Long size;
	private String address;
	private String firstName;
	private String lastName;
	private String jmbg;

	public InsurancePolicyHomeRequest() {
		super();
	}

	public InsurancePolicyHomeRequest(Integer duration, Long risk, Long value, Long age, Long size, String address,
			String firstName, String lastName, String jmbg) {
		super();
		this.duration = duration;
		this.risk = risk;
		this.value = value;
		this.age = age;
		this.size = size;
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Long getRisk() {
		return risk;
	}

	public void setRisk(Long risk) {
		this.risk = risk;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

}
