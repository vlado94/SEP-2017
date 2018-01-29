package da.converter;

import java.util.ArrayList;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import da.insurancePolicyFinal.InsurancePolicyFinal;
import da.insurancePolicyPrice.Discount;
import da.person.Person;
import model.request.PersonRequest;
import model.response.InsurancePolicyCalculatePriceResponse;
import model.response.InsurancePolicyCheckoutResponse;

@Component
public class InsurancePolicyFinalToInsurancePolicyCheckoutResponse
		implements Converter<InsurancePolicyFinal, InsurancePolicyCheckoutResponse> {

	@Override
	public InsurancePolicyCheckoutResponse convert(InsurancePolicyFinal source) {
		InsurancePolicyCheckoutResponse response = new InsurancePolicyCheckoutResponse();
		response.setAddress(source.getInsurancePolicyHome().getAddress());
		response.setAge(source.getInsurancePolicyHome().getAge());
		response.setAmount(source.getAmount());
		response.setCarBrand(source.getInsurancePolicyCar().getCarBrand());
		response.setChassisNumber(source.getInsurancePolicyCar().getChassisNumber());
		response.setDurationForCar(source.getInsurancePolicyCar().getDurationForCar());
		response.setDurationForHome(source.getInsurancePolicyHome().getDurationForHome());
		response.setDurationForTravel(source.getDurationForTravel());
		response.setEmailEmployee(source.getEmailEmployee());
		response.setFirstNameOwnerCar(source.getInsurancePolicyCar().getFirstNameOwnerCar());
		response.setFirstNameOwnerHome(source.getInsurancePolicyHome().getFirstNameOwnerHome());
		response.setJmbgOwnerCar(source.getInsurancePolicyCar().getJmbgOwnerCar());
		response.setJmbgOwnerHome(source.getInsurancePolicyHome().getJmbgOwnerHome());
		response.setLastNameOwnerCar(source.getInsurancePolicyCar().getLastNameOwnerCar());
		response.setLastNameOwnerHome(source.getInsurancePolicyHome().getLastNameOwnerHome());

		response.setPersons(new ArrayList<PersonRequest>());
		for (Person p : source.getPersons()) {
			PersonRequest pRequest = new PersonRequest();
			pRequest.setAddress(p.getAddress());
			pRequest.setContractor(p.getContractor());
			pRequest.setEmail(p.getEmail());
			pRequest.setFirstName(p.getFirstName());
			pRequest.setLastName(p.getLastName());
			pRequest.setPassportNo(p.getPassportNumber());
			pRequest.setPersonNo(p.getJmbg());
			pRequest.setPhone(p.getPhone());

			response.getPersons().add(pRequest);
		}

		response.setPopravka(source.getInsurancePolicyCar().getPopravka());
		response.setPrevoz(source.getInsurancePolicyCar().getPrevoz());
		if (source.getInsurancePolicyCar() != null && source.getInsurancePolicyCar().getPrice() != null) {
			InsurancePolicyCalculatePriceResponse priceCar = new InsurancePolicyCalculatePriceResponse();
			priceCar.setBasePrice(source.getInsurancePolicyCar().getPrice().getBasePrice());
			priceCar.setFinalPrice(source.getInsurancePolicyCar().getPrice().getFinalPrice());
			priceCar.setDiscounts(new ArrayList<model.dto.Discount>());
			for (Discount d : source.getInsurancePolicyCar().getPrice().getDiscounts()) {
				model.dto.Discount newD = new model.dto.Discount(d.getPercent(), d.getDiscountName(), d.getAmount());
				priceCar.getDiscounts().add(newD);
			}
			response.setPriceAndDiscountsForCar(priceCar);
		}
		
		InsurancePolicyCalculatePriceResponse priceTravel = new InsurancePolicyCalculatePriceResponse();
		priceTravel.setBasePrice(source.getPrice().getBasePrice());
		priceTravel.setFinalPrice(source.getPrice().getFinalPrice());
		priceTravel.setDiscounts(new ArrayList<model.dto.Discount>());
		for (Discount d : source.getPrice().getDiscounts()) {
			model.dto.Discount newD = new model.dto.Discount(d.getPercent(), d.getDiscountName(), d.getAmount());
			priceTravel.getDiscounts().add(newD);
		}
		response.setPriceAndDiscountsForTravel(priceTravel);
		response.setPriceForHome(source.getInsurancePolicyHome().getPriceForHome());
		response.setRegion(source.getRegion());
		response.setRegistrationNumber(source.getInsurancePolicyCar().getRegistrationNumber());
		response.setRisk(source.getInsurancePolicyHome().getRisk());
		response.setSize(source.getInsurancePolicyHome().getSize());
		response.setSlepovanje(source.getInsurancePolicyCar().getSlepovanje());
		response.setSmestaj(source.getInsurancePolicyCar().getSmestaj());
		response.setSport(source.getSport());
		response.setStartDate(source.getStartDate());
		response.setTotalPrice(source.getTotalPrice());
		response.setTypeOfPolicy(source.getTypeOfPolicy());
		response.setTypeOfVehicle(source.getInsurancePolicyCar().getTypeOfVehicle());
		response.setValue(source.getInsurancePolicyHome().getValue());
		response.setYear(source.getInsurancePolicyCar().getYear());

		return response;
	}

}
