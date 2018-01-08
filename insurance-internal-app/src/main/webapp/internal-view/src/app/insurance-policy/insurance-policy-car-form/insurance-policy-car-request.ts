export class InsurancePolicyCarRequest {
    duration: number;
    paket: string;
    vehicle: string;
    typeOfVehicle: string;
    year: number;
    registrationNumber: string;
    chassisNumber: string;
    firstName: string;
    lastName: string;
    jmbg: string;

    constructor(duration: number,
        paket: string,
        vehicle: string,
        typeOfVehicle: string,
        year: number,
        registrationNumber: string,
        chassisNumber: string,
        firstName: string,
        lastName: string,
        jmbg: string) {
        this.duration = duration;
        this.paket = paket;
        this.vehicle = vehicle;
        this.typeOfVehicle = typeOfVehicle;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.chassisNumber = chassisNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;
    }
}