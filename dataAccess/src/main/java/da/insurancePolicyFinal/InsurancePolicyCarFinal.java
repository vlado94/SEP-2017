package da.insurancePolicyFinal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import da.insurancePolicyPrice.InsurancePolicyPrice;

@Entity
public class InsurancePolicyCarFinal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	@OneToOne(cascade = CascadeType.ALL)
	private InsurancePolicyPrice price;
	
	public InsurancePolicyCarFinal() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public InsurancePolicyPrice getPrice() {
		return price;
	}
	public void setPrice(InsurancePolicyPrice price) {
		this.price = price;
	}
	
	
}
