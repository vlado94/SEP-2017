export class InsurancePolicyPersonRequest {
    firstName: string;
    lastName: string;
    personNo: string;
    passportNo: string;
    address: string;
    phone: string;
    contractor: boolean;
    email: string;
    constructor(firstName: string,
        lastName: string,
        personNo: string,
        passportNo: string,
        address: string,
        phone: string,
        contractor: boolean,
        email: string) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.personNo = personNo;
        this.passportNo = passportNo;
        this.address = address;
        this.phone = phone;
        this.contractor = contractor;
        this.email = email;
    }
}