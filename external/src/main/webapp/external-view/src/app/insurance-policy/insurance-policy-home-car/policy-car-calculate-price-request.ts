export class InsurancePolicyCarCalculatePriceRequest {

    duration: number;
    smestaj:number;
    popravka: number;
    prevoz: number;
    slepovanje: number;    
    constructor(duration:number,slepovanje: number, smestaj: number, popravka: number, prevoz: number){
        this.duration = duration;
        this.slepovanje = slepovanje;
        this.smestaj = smestaj;
        this.popravka = popravka;
        this.prevoz = prevoz;    
    
    }
}