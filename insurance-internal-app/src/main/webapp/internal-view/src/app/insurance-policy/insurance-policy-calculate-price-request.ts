export class InsurancePolicyCalculatePriceRequest {
    startDate: string;
    duration: number;
    region: string;
    sport: string;
    amount: string;
    typeOfPolicy: string;
    firstAgeCategory: number;
    secondAgeCategory: number;
    thirdAgeCategory: number;

    constructor(startDate: string, duration: number,
        region: string, sportId: string,
        amountId: string, typeOfPolicy: string,
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