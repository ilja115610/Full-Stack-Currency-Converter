import { Component, OnInit } from '@angular/core';
import {HistoryService} from "../../services/history.service";
import {AuthService} from "../../services/auth.service";
import {Record} from "../../common/record";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {


  records: Record [] = [];

  currencyNameSearch: string;

  constructor(private historyService: HistoryService, private authService: AuthService) { }

  ngOnInit(): void {

    this.historyService.findUserHistory(this.authService.getUserId()).subscribe((data=>{
      this.records = data;
    }))
  }



}
