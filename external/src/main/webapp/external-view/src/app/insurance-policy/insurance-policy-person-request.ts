export class InsurancePolicyPersonRequest {
    firstName: string;
    lastName: string;
    personNo: string;
    passportNo: string;
    address: string;
    phoneNo: string;
    contractor: boolean;
    email: string;
    constructor(firstName: string,
        lastName: string,
        personNo: string,
        passportNo: string,
        address: string,
        phoneNo: string,
        contractor: boolean,
        email: string) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.personNo = personNo;
        this.passportNo = passportNo;
        this.address = address;
        this.phoneNo = phoneNo;
        this.contractor = contractor;
        this.email = email;
    }
}