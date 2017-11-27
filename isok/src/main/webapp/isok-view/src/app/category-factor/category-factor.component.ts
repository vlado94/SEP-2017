import { Component, OnInit, Input } from '@angular/core';
import { Http, Response } from "@angular/http";

import { CategoryFactorService } from "./category-factor.service";
import { CategoryFactor } from './category-factor';

@Component({
  selector: 'app-category-factor',
  templateUrl: './category-factor.component.html',
  styleUrls: ['./category-factor.component.css'],
  providers: [CategoryFactorService],
})
export class CategoryFactorComponent implements OnInit {

	categoryFactors : Array<CategoryFactor>;;
	constructor(
		private categoryFactorService: CategoryFactorService,
		private http: Http
	) { }

	ngOnInit() {  
		this.categoryFactorService.findAll()
	  		.subscribe(categoryFactors => 
				this.categoryFactors = categoryFactors);
	}
}
