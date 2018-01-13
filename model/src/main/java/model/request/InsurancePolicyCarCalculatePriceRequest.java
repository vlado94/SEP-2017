package model.request;

public class InsurancePolicyCarCalculatePriceRequest {

	private Integer duration;
	private Long slepovanje;
	private Long popravka;
	private Long smestaj;
	private Long prevoz;
	
	public InsurancePolicyCarCalculatePriceRequest() {
		super();
	}
	public InsurancePolicyCarCalculatePriceRequest(Integer duration, Long slepovanje, Long popravka, Long smestaj,
			Long prevoz) {
		super();
		this.duration = duration;
		this.slepovanje = slepovanje;
		this.popravka = popravka;
		this.smestaj = smestaj;
		this.prevoz = prevoz;
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
	public Long getSmestaj() {
		return smestaj;
	}
	public void setSmestaj(Long smestaj) {
		this.smestaj = smestaj;
	}
	public Long getPrevoz() {
		return prevoz;
	}
	public void setPrevoz(Long prevoz) {
		this.prevoz = prevoz;
	}



}
