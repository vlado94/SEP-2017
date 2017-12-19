import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from "./user";
import { UserDataService } from './user-data.service';

@Component({
	selector: 'app-user-data',
	templateUrl: './user-data.component.html',
	styleUrls: ['./user-data.component.scss']
})
export class UserDataComponent implements OnInit {

	users : User[] = [];
	//user : User;
	userDataForm: FormGroup;
	userDataFormValue: User;
	constructor(private userDataService: UserDataService ) { }

	ngOnInit() {
		this.userDataForm = new FormGroup({
			firstName: new FormControl('', Validators.required),
			lastName: new FormControl('', Validators.required),
			personNo: new FormControl('', [Validators.required, Validators.pattern("[0-9]+")]),
			passportNo: new FormControl('', [Validators.required, Validators.pattern("[0-9]+")]),
			address: new FormControl('', Validators.required),
			phoneNo: new FormControl('', [Validators.required, Validators.pattern("[0-9]+")])

		});

	}

	addUser(){
		if(this.userDataForm.valid){
			this.userDataFormValue = this.userDataForm.value;
			this.users.push(this.userDataFormValue);
			this.ngOnInit();
		} else {
			alert("Greka")
		}
	}

}
