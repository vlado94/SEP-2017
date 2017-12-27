export class InsurancePolicyHomeCalculatePriceRequest {

    duration: number;
    size: number;
    age: number;
    value: number;
    risk: number;

    constructor(duration: number, size: number, age: number, value: number, risk: number) {
        this.duration = duration;
        this.size = size;
        this.age = age;
        this.value = value;
        this.risk = risk;
    }
}