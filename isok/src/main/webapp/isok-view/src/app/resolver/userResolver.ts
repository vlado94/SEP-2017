import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { ResolverServiceService } from './resolver-service.service'

@Injectable()
export class Resolver implements Resolve<any> {

	data: boolean;

	constructor(private resolverServiceService: ResolverServiceService) {}

	resolve(route: ActivatedRouteSnapshot) {
		return this.resolverServiceService.getRoles().subscribe(
				data => {this.data = data; 
						if(this.data == true)
							return true;
						else
							history.back();
					}
			)
	}
}