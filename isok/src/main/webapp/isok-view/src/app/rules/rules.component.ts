import { Component, OnInit } from '@angular/core';
import { RulesService } from './rules.service';


@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.css']
})
export class RulesComponent implements OnInit {

  private s: string;

  constructor(private  rulesService: RulesService) {

   }

  ngOnInit() {
    this.getRules();
  }

  getRules() {
     this.rulesService.getRules().subscribe(s =>
      this.s = s);

  }

  change(s) {
    //this.rulesService.changeRules(document.getElementById('textArea1').value);
    this.rulesService.changeRules('sasa');
    // this.getRules();
    alert('prosao ovo');
  }

}
