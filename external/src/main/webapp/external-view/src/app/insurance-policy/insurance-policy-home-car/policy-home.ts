export class InsurancePolicyHome {
    duration: number;
    size: string;
    age: string;
    value: string;
    risk: string;
    address: string;
    firstName: string;
    lastName: string;
    jmbg: string;
    constructor(duration: number, size: string, age: string, value: string, risk: string,
        address: string, firstName: string, lastName: string, jmbg: string) {
        this.duration = duration;
        this.size = size;
        this.age = age;
        this.value = value;
        this.risk = risk;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;

    }
}