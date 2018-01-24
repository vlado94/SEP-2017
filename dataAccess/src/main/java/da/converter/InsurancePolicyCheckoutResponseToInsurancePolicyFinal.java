package da.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import da.insurancePolicyFinal.InsurancePolicyCarFinal;
import da.insurancePolicyFinal.InsurancePolicyFinal;
import da.insurancePolicyFinal.InsurancePolicyHomeFinal;
import da.insurancePolicyPrice.Discount;
import da.insurancePolicyPrice.InsurancePolicyPrice;
import da.person.Person;
import model.request.PersonRequest;
import model.response.InsurancePolicyCheckoutResponse;

@Component
public class InsurancePolicyCheckoutResponseToInsurancePolicyFinal
		implements Converter<InsurancePolicyCheckoutResponse, InsurancePolicyFinal> {

	@Override
	public InsurancePolicyFinal convert(InsurancePolicyCheckoutResponse source) {
		// TODO Auto-generated method stub
		InsurancePolicyFinal insurancePolicyFinal = new InsurancePolicyFinal();

		// INSURANCE POLICY
		insurancePolicyFinal.setAmount(source.getAmount());
		insurancePolicyFinal.setDurationForTravel(source.getDurationForTravel());
		insurancePolicyFinal.setPaid(false);
		insurancePolicyFinal.setRegion(source.getRegion());
		insurancePolicyFinal.setSport(source.getSport());
		insurancePolicyFinal.setStartDate(source.getStartDate());
		insurancePolicyFinal.setTotalPrice(source.getTotalPrice());
		insurancePolicyFinal.setTypeOfPolicy(source.getTypeOfPolicy());

		// INSURANCE POLICY CAR
		InsurancePolicyCarFinal insurancePolicyCarFinal = new InsurancePolicyCarFinal();
		insurancePolicyCarFinal.setChassisNumber(source.getChassisNumber());
		insurancePolicyCarFinal.setDurationForCar(source.getDurationForCar());
		insurancePolicyCarFinal.setFirstNameOwnerCar(source.getFirstNameOwnerCar());
		insurancePolicyCarFinal.setLastNameOwnerCar(source.getLastNameOwnerCar());
		insurancePolicyCarFinal.setPopravka(source.getPopravka());
		insurancePolicyCarFinal.setPrevoz(source.getPrevoz());
		// car price
		if (source.getPriceAndDiscountsForCar() != null) {
			InsurancePolicyPrice carPrice = new InsurancePolicyPrice();
			carPrice.setBasePrice(source.getPriceAndDiscountsForCar().getBasePrice());
			carPrice.setFinalPrice(source.getPriceAndDiscountsForCar().getFinalPrice());
			for (model.dto.Discount d : source.getPriceAndDiscountsForCar().getDiscounts()) {
				Discount discount = new Discount();
				discount.setAmount(d.getAmount());
				discount.setDiscountName(d.getDiscountName());
				discount.setPercent(d.getPercent());
				carPrice.getDiscounts().add(discount);
			}
			insurancePolicyCarFinal.setPrice(carPrice);

		}
		insurancePolicyCarFinal.setRegistrationNumber(source.getRegistrationNumber());
		insurancePolicyCarFinal.setSlepovanje(source.getSlepovanje());
		insurancePolicyCarFinal.setSmestaj(source.getSmestaj());
		insurancePolicyCarFinal.setTypeOfVehicle(source.getTypeOfPolicy());
		insurancePolicyCarFinal.setYear(source.getYear());

		insurancePolicyFinal.setInsurancePolicyCar(insurancePolicyCarFinal);
		/////////////////

		/// INSURANCE POLICY HOME
		InsurancePolicyHomeFinal insurancePolicyHomeFinal = new InsurancePolicyHomeFinal();
		insurancePolicyHomeFinal.setAddress(source.getAddress());
		insurancePolicyHomeFinal.setAge(source.getAge());
		insurancePolicyHomeFinal.setDurationForHome(source.getDurationForHome());
		insurancePolicyHomeFinal.setFirstNameOwnerHome(source.getFirstNameOwnerHome());
		insurancePolicyHomeFinal.setLastNameOwnerHome(source.getLastNameOwnerHome());
		insurancePolicyHomeFinal.setPriceForHome(source.getPriceForHome());
		insurancePolicyHomeFinal.setRisk(source.getRisk());
		insurancePolicyHomeFinal.setSize(source.getSize());
		insurancePolicyHomeFinal.setValue(source.getValue());

		insurancePolicyFinal.setInsurancePolicyHome(insurancePolicyHomeFinal);
		/////////////////

		for (PersonRequest p : source.getPersons()) {
			Person person = new Person();
			person.setAddress(p.getAddress());
			person.setContractor(p.isContractor());
			person.setEmail(p.getEmail());
			person.setFirstName(p.getFirstName());
			person.setLastName(p.getLastName());
			person.setJmbg(p.getPersonNo());
			person.setLastName(p.getLastName());
			person.setPassportNumber(p.getPassportNo());
			person.setPhone(p.getPhone());

			insurancePolicyFinal.getPersons().add(person);
		}

		// POLICY PRICE
		InsurancePolicyPrice policyPrice = new InsurancePolicyPrice();
		policyPrice.setBasePrice(source.getPriceAndDiscountsForTravel().getBasePrice());
		policyPrice.setFinalPrice(source.getPriceAndDiscountsForTravel().getFinalPrice());
		for (model.dto.Discount d : source.getPriceAndDiscountsForTravel().getDiscounts()) {
			Discount discount = new Discount();
			discount.setAmount(d.getAmount());
			discount.setDiscountName(d.getDiscountName());
			discount.setPercent(d.getPercent());
			policyPrice.getDiscounts().add(discount);
		}
		insurancePolicyFinal.setPrice(policyPrice);
		return insurancePolicyFinal;
	}

}
