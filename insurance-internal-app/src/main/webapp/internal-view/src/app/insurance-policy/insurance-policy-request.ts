import {InsurancePolicyPersonRequest} from './insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';


export class InsurancePolicyRequest {
    startDate: string;
    duration: number;
    region: string;
    sport: string;
    amount: string;
    typeOfPolicy: string;
    firstAgeCategory: number;
    secondAgeCategory: number;
    thirdAgeCategory: number;

    persons: InsurancePolicyPersonRequest[] = [];
    constructor(startDate: string, duration: number,
        region: string, sportId: string,
        amountId: string, typeOfPolicy: string, numberOfPersons: number,
        firstAgeCategory: number,
        secondAgeCategory: number,
        thirdAgeCategory: number) {
        this.startDate = startDate;
        this.duration = duration;
        this.region = region;
        this.sport = sportId;
        this.amount = amountId;
        this.firstAgeCategory = firstAgeCategory;
        this.secondAgeCategory = secondAgeCategory;
        this.thirdAgeCategory = thirdAgeCategory;
        this.typeOfPolicy = typeOfPolicy;
    }
}