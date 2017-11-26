import { Component, OnInit, Input,ViewChild  } from '@angular/core';
import { Http, Response } from "@angular/http";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { FactorService } from "../factor.service";
import { FactorComponent } from "../factor.component";
import { FactorListComponent } from "../factor-list/factor-list.component";
import { Factor } from "../factor";
import { NgForm } from '@angular/forms';

@Component({
  	selector: 'app-factor-form',
    templateUrl: './factor-form.component.html',
    styleUrls: ['./factor-form.component.css'],
})
export class FactorFormComponent implements OnInit {
    factorForm: FormGroup;
    factor : Factor  = new Factor();
    factorValue : Factor  = new Factor();
    title: string;

	constructor(
        formBuilder: FormBuilder,
        private factorService: FactorService,
        private route: ActivatedRoute,
        private http: Http) {
            this.factorForm = formBuilder.group({
                name: ['', [
                    Validators.required,
                    Validators.minLength(3)
                ]],
            });
    }

    ngOnInit() {
	    var id = this.route.params.subscribe(params => {
            var id = params['id'];
            this.title = id ? 'Edit factor' : 'New factor';
            if (!id)
              return;

            this.factorService.get(id)
                .subscribe(
                    factor => this.factor = factor,
                );
        });
    }

  	save(data) {
        this.route.params.subscribe(params => {
            var id = params['id'];        
            this.factorValue = this.factorForm.value;
            if (id !== undefined){
                this.factorValue.id = parseInt(id);
                this.factorService.update(this.factorValue);
            } else {
                this.factorService.save(this.factorValue);
            }
        })

    }
}
