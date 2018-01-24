
export class PaymentRequest {
    holderName: string;
    cardNum: string;
    expDate: string;
    cvv2: string;
    

    constructor(holderName: string, cardNum: string,
        expDate: string, cvv2: string) {
        this.holderName = holderName;
        this.cardNum = cardNum;
        this.expDate = expDate;
        this.cvv2 = cvv2;
    }
}