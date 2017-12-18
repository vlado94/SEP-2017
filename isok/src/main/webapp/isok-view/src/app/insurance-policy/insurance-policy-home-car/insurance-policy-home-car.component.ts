import {Component, Input, Output, EventEmitter,NgModule} from '@angular/core';

@Component({

    selector: 'app-insurance-policy-home-car',
    templateUrl: './insurance-policy-home-car.component.html',
    styleUrls: ['./insurance-policy-home-car.component.css']
})

@NgModule({
    imports: [],
    exports: [],
    declarations: [],
    providers: []

})

export class InsurancePolicyHomeCar{
    
    activeForm:string = null;

    showForm(value){
        this.activeForm = value;    
    }
}