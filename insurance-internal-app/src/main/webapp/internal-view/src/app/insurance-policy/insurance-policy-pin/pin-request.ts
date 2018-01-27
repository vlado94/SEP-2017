export class PinRequest {
    cardHolder: number;
    totalPrice: number;
    pin: number;

    constructor(cardHolder: number, totalPrice:number, pin: number) {
        this.cardHolder = cardHolder,
        this.totalPrice = totalPrice;
        this.pin = pin
    }
}