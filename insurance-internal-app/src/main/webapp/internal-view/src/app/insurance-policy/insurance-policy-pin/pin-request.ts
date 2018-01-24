export class PinRequest {
    cardHolder: string;
    pin: number;

    constructor(cardHolder: string, pin: number) {
        this.cardHolder = cardHolder,
        this.pin = pin
    }
}