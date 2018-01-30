
export class PaymentRequest {
    holderName: string;
    cardNum: string;
    expDate: string;
    cvv2: string;
    policyID: string;
    policyPrice: number;
    

    constructor(holderName: string, cardNum: string,
        expDate: string, cvv2: string, policyID: string, policyPrice: number) {
        this.holderName = holderName;
        this.cardNum = cardNum;
        this.expDate = expDate;
        this.cvv2 = cvv2;
        this.policyID = policyID;
        this.policyPrice = policyPrice;
    }
}