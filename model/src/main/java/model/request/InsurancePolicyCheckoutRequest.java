package model.request;

public class InsurancePolicyCheckoutRequest {

	private InsurancePolicyRequest insurancePolicyRequest;
	private InsurancePolicyCarRequest insurancePolicyCarRequest;
	private InsurancePolicyHomeRequest insurancePolicyHomeRequest;

	public InsurancePolicyCheckoutRequest() {
		super();
	}

	public InsurancePolicyCheckoutRequest(InsurancePolicyRequest insurancePolicyRequest,
			InsurancePolicyCarRequest insurancePolicyCarRequest,
			InsurancePolicyHomeRequest insurancePolicyHomeRequest) {
		super();
		this.insurancePolicyRequest = insurancePolicyRequest;
		this.insurancePolicyCarRequest = insurancePolicyCarRequest;
		this.insurancePolicyHomeRequest = insurancePolicyHomeRequest;
	}

	public InsurancePolicyRequest getInsurancePolicyRequest() {
		return insurancePolicyRequest;
	}

	public void setInsurancePolicyRequest(InsurancePolicyRequest insurancePolicyRequest) {
		this.insurancePolicyRequest = insurancePolicyRequest;
	}

	public InsurancePolicyCarRequest getInsurancePolicyCarRequest() {
		return insurancePolicyCarRequest;
	}

	public void setInsurancePolicyCarRequest(InsurancePolicyCarRequest insurancePolicyCarRequest) {
		this.insurancePolicyCarRequest = insurancePolicyCarRequest;
	}

	public InsurancePolicyHomeRequest getInsurancePolicyHomeRequest() {
		return insurancePolicyHomeRequest;
	}

	public void setInsurancePolicyHomeRequest(InsurancePolicyHomeRequest insurancePolicyHomeRequest) {
		this.insurancePolicyHomeRequest = insurancePolicyHomeRequest;
	}

}
