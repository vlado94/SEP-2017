export class PinRequest {
    cardHolder: number;
    totalPrice: number;
    pin: number;
    policyId:number;

    constructor(cardHolder: number, totalPrice:number, pin: number, policyId:number) {
        this.cardHolder = cardHolder,
        this.totalPrice = totalPrice;
        this.pin = pin
        this.policyId = policyId;
    }
}