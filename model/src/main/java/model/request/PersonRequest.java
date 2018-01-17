package model.request;

public class PersonRequest {
	String firstName;
	String lastName;
	String personNo;
	String passportNo;
	String address;
	String phone;
	Long age;
	boolean contractor;
	String email;
	

	public PersonRequest() {
		super();
	}

	public PersonRequest(String firstName, String lastName, String personNo, String passportNo, String address,
			String phone, Long age,boolean contractor, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.personNo = personNo;
		this.passportNo = passportNo;
		this.address = address;
		this.phone = phone;
		this.age = age;
		this.contractor = contractor;
		this.email = email;
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

	public String getPersonNo() {
		return personNo;
	}

	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public boolean isContractor() {
		return contractor;
	}

	public void setContractor(boolean contractor) {
		this.contractor = contractor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
