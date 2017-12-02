import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";

import { Observable } from 'rxjs/Observable';
import {ActivatedRoute, Router} from '@angular/router';

import { FactorComponent } from "../factor.component";

@Component({
    selector: 'app-factor-list',
    templateUrl: './factor-list.component.html',
    styleUrls: ['./factor-list.component.css'],
})
export class FactorListComponent implements OnInit {
    title = "Factors";
    constructor(
        private factorComponent: FactorComponent,
        private route: ActivatedRoute
    ) { }

	ngOnInit() { 
    }
    
  
}
