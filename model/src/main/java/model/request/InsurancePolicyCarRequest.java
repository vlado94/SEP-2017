package model.request;

public class InsurancePolicyCarRequest {
	private Integer duration;
	private Long slepovanje;
	private Long popravka;
	private Long prevoz;
	private Long smestaj;
	private String vehicle;
	private String typeOfVehicle;
	private Integer year;
	private String registrationNumber;
	private String chassisNumber;
	private String firstName;
	private String lastName;
	private String personNo;

	public InsurancePolicyCarRequest() {
		super();
	}

	public InsurancePolicyCarRequest(Integer duration, Long slepovanje, Long popravka, Long prevoz, Long smestaj,
			String vehicle, String typeOfVehicle, Integer year, String registrationNumber, String chassisNumber,
			String firstName, String lastName, String jmbg) {
		super();
		this.duration = duration;
		this.slepovanje = slepovanje;
		this.popravka = popravka;
		this.prevoz = prevoz;
		this.smestaj = smestaj;
		this.vehicle = vehicle;
		this.typeOfVehicle = typeOfVehicle;
		this.year = year;
		this.registrationNumber = registrationNumber;
		this.chassisNumber = chassisNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.personNo = jmbg;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Long getSlepovanje() {
		return slepovanje;
	}

	public void setSlepovanje(Long slepovanje) {
		this.slepovanje = slepovanje;
	}

	public Long getPopravka() {
		return popravka;
	}

	public void setPopravka(Long popravka) {
		this.popravka = popravka;
	}

	public Long getPrevoz() {
		return prevoz;
	}

	public void setPrevoz(Long prevoz) {
		this.prevoz = prevoz;
	}

	public Long getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Long smestaj) {
		this.smestaj = smestaj;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
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

	public void setPersonNo(String jmbg) {
		this.personNo = jmbg;
	}

}
