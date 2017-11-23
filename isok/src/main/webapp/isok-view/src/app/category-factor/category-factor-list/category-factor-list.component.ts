import { Component, OnInit, Input } from '@angular/core';
import {CategoryFactorService} from "../category-factor.service";
import { Http, Response } from "@angular/http";
import { CategoryFactors } from '../category-factor-list';
import { FormControl, FormGroup, Validators } from "@angular/forms";



@Component({
    selector: 'app-category-factor-list',
    templateUrl: './category-factor-list.component.html',
    styleUrls: ['./category-factor-list.component.css'],
    providers: [CategoryFactorService],
})
export class CategoryFactorListComponent implements OnInit {

    categoryFactors = CategoryFactors;
    categoryFactorForm: FormGroup;
    updateCategoryFactorForm: FormGroup;
    categoryFactor;
    categoryFactorResponse;
    updateForm: boolean; createForm: boolean;

    constructor(private categoryFactorService: CategoryFactorService, private http: Http) { }

    ngOnInit() {
        this.getAllCategoryFactors();
        this.categoryFactorForm = new FormGroup({
            name: new FormControl('', Validators.required)
        });
        this.updateForm = false;
        this.createForm = true;
        this.updateCategoryFactorForm = new FormGroup({
            id: new FormControl('', Validators.required),
            name: new FormControl('', Validators.required),

        });
    }

    getAllCategoryFactors() {
        this.categoryFactorService.findAll()
            .subscribe(categoryFactors => {
                this.categoryFactors = categoryFactors
            },
            err => { console.log(err); });

    }

    createCategoryFactor() {
        if (this.categoryFactorForm.valid) {
            this.categoryFactor = this.categoryFactorForm.value;
            this.categoryFactorService.saveCategory(this.categoryFactor)
                .subscribe(
                categoryFactor => {
                    this.categoryFactorResponse = categoryFactor,
                        this.categoryFactors.push(this.categoryFactorResponse);
                },
                err => { console.log(err); });

            console.log(this.categoryFactors);
        }

        this.categoryFactorForm.reset();

    }

    deleteCategory(categoryFactor) {
        this.categoryFactorService.deleteById(categoryFactor.id).subscribe(
            res => {
                const index: number = this.categoryFactors.indexOf(categoryFactor);
                if (index !== -1) {
                    this.categoryFactors.splice(index, 1);
                }
                console.log('done');
            }
        );

    }

    findCategory(categoryFactor) {
        this.categoryFactorService.findById(categoryFactor.id).subscribe(
            categoryUpdate => {
                this.updateCategoryFactorForm.patchValue({
                    id: categoryUpdate.id,
                    name: categoryUpdate.name,
                });

            }, error => {
                console.log(error);
            }
        );
        this.updateForm = true;
        this.createForm = false;

    }

    updateCategoryFactor() {
        if (this.updateCategoryFactorForm.valid) {
            this.categoryFactorService.updateCategoryFactor(this.updateCategoryFactorForm.value).subscribe()
            this.getAllCategoryFactors();
            this.updateForm = false;
            this.createForm = true;
        }

    }

    closeUpdate() {
        this.updateForm = false;
        this.createForm = true;
    }


}