export class InsurancePolicyCar {
    duration: number;
    slepovanje: string;
    popravka: string;
    prevoz: string;
    smestaj: string;
    vehicle: string;
    typeOfVehicle: string;
    year: number;
    registrationNumber: string;
    chassisNumber: string;
    firstName: string;
    lastName: string;
    personNo: string;

    constructor(duration: number,
        slepovanje:string,
        prevoz: string,
        popravka: string,
        smestaj: string, 
        vehicle: string,
        typeOfVehicle: string,
        year: number,
        registrationNumber: string,
        chassisNumber: string,
        firstName: string,
        lastName: string,
        personNo: string) {
        this.duration = duration;
        this.slepovanje = slepovanje;
        this.prevoz = prevoz;
        this.smestaj = smestaj;
        this.popravka = popravka; 
        this.vehicle = vehicle;
        this.typeOfVehicle = typeOfVehicle;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.chassisNumber = chassisNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personNo = personNo;
    }
}