package da.rules;

public class Popust {

	public String getNazivPopusta() {
		return nazivPopusta;
	}
	public void setNazivPopusta(String nazivPopusta) {
		this.nazivPopusta = nazivPopusta;
	}
	public double getIznosPopusta() {
		return iznosPopusta;
	}
	public void setIznosPopusta(double iznosPopusta) {
		this.iznosPopusta = iznosPopusta;
	}
	private String nazivPopusta;
	private double iznosPopusta;
	public Popust() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Popust(String s,double d) {
		this.nazivPopusta=s;
		this.iznosPopusta=d;
	}
	
	
	
	
	
}
