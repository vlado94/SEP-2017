import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";

import { Observable } from 'rxjs/Observable';
import {ActivatedRoute, Router} from '@angular/router';

import { CategoryFactorComponent } from "../category-factor.component";

@Component({
    selector: 'app-category-factor-list',
    templateUrl: './category-factor-list.component.html',
    styleUrls: ['./category-factor-list.component.css']
})
export class CategoryFactorListComponent implements OnInit {
	  title = "Category factors";
	  constructor(
        private categoryFactorComponent: CategoryFactorComponent,
        private router: Router
    ) { }

   	ngOnInit() { 
    }
}
