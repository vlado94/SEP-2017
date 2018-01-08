import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-parent-policy',
	templateUrl: './parent-policy.component.html',
	styleUrls: ['./parent-policy.component.scss']
})
export class ParentPolicyComponent implements OnInit {

	active_tab: number;

	constructor() { }

	ngOnInit() {
		this.active_tab = 1;
	}

}
