
export class PaymentRequest {
    holderName: string;
    cardNum: string;
    expDate: string;
    cvv2: string;
    policyID: string;
    policyPrice: string;
    

    constructor(holderName: string, cardNum: string,
        expDate: string, cvv2: string, policyID: string, policyPrice: string) {
        this.holderName = holderName;
        this.cardNum = cardNum;
        this.expDate = expDate;
        this.cvv2 = cvv2;
        this.policyID = policyID;
        this.policyPrice = policyPrice;
    }
}