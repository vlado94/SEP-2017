export class User {

    firstName: string;
    lastName: string;
    passportNo: number;
    address:string;
    phoneNo: number;

    constructor(firstName: string, lastName: string, passportNo:number, address: string, phoneNo: number){
      this.firstName = firstName;
      this.lastName = lastName;
      this.passportNo = passportNo;
      this.address = address;
      this.phoneNo = phoneNo;
    }

}