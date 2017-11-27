import { Component, OnInit, Input } from '@angular/core';
import { Http, Response } from "@angular/http";

import { FactorService } from "./factor.service";
import { Factor } from './factor';

@Component({
  selector: 'app-factor',
  templateUrl: './factor.component.html',
  styleUrls: ['./factor.component.css'],
  providers: [FactorService],
})
export class FactorComponent implements OnInit {

	factors : Array<Factor>;
	constructor(
		private factorService: FactorService,
		private http: Http
	) { }

	ngOnInit() {  
		this.factorService.findAll()
	  		.subscribe(factors => 
				this.factors = factors);
	}
}
