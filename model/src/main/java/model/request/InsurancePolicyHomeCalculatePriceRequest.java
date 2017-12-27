package model.request;

public class InsurancePolicyHomeCalculatePriceRequest {

	private Integer duration;
	private Long size;
	private Long age;
	private Long value;
	private Long risk;
	
	public InsurancePolicyHomeCalculatePriceRequest() {
		super();
	}
	public InsurancePolicyHomeCalculatePriceRequest(Integer duration, Long size, Long age, Long value, Long risk) {
		super();
		this.duration = duration;
		this.size = size;
		this.age = age;
		this.value = value;
		this.risk = risk;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public Long getRisk() {
		return risk;
	}
	public void setRisk(Long risk) {
		this.risk = risk;
	}
	
	
}
