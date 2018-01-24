package model.response;


public class PersonResponse {

	private String firstName;
	private String lastName;
	private String jmbg;
	private Double sportPrice;
	private Double regionPrice;
	private Double agePrice;
	private Double coverPrice;
	private Double totalPrice;
	
	public PersonResponse() {
		
	}

	public PersonResponse(String firstName, String lastName, String jmbg, Double sportPrice, Double regionPrice,
			Double agePrice, Double totalPrice,Double coverPrice) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.sportPrice = sportPrice;
		this.regionPrice = regionPrice;
		this.agePrice = agePrice;
		this.totalPrice = totalPrice;
		this.coverPrice  = coverPrice;
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

	public Double getSportPrice() {
		return sportPrice;
	}

	public void setSportPrice(Double sportPrice) {
		this.sportPrice = sportPrice;
	}

	public Double getRegionPrice() {
		return regionPrice;
	}

	public void setRegionPrice(Double regionPrice) {
		this.regionPrice = regionPrice;
	}

	public Double getAgePrice() {
		return agePrice;
	}

	public void setAgePrice(Double agePrice) {
		this.agePrice = agePrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getCoverPrice() {
		return coverPrice;
	}

	public void setCoverPrice(Double coverPrice) {
		this.coverPrice = coverPrice;
	}

}
