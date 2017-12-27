package model.request;

public class InsurancePolicyCarCalculatePriceRequest {

	private Integer duration;
	private Long paket;

	public InsurancePolicyCarCalculatePriceRequest() {
		super();
	}

	public InsurancePolicyCarCalculatePriceRequest(Integer duration, Long paket) {
		super();
		this.duration = duration;
		this.paket = paket;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Long getPaket() {
		return paket;
	}

	public void setPaket(Long paket) {
		this.paket = paket;
	}

}
