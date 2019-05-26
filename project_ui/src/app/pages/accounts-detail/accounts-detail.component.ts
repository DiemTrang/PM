import { Component, OnInit } from '@angular/core';
import { AccountProvider } from 'src/app/providers';
import { HTTP } from '../../../app/utilities';
import { Params, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accounts-detail',
  templateUrl: './accounts-detail.component.html',
  styleUrls: ['./accounts-detail.component.css']
})
export class AccountsDetailComponent implements OnInit {
  public data = [];
  public id = 0;

  constructor(
    private acc: AccountProvider,
    private act: ActivatedRoute,) { }

  ngOnInit() {
    this.act.params.subscribe((params: Params) => {
      this.id = params["_id"];
      this.getAccountsDetail(this.id);
  });
  }

  public getAccountsDetail(id: any) {
    this.acc.getAccountsDetail(id).subscribe((rsp: any) => {
        
        if (rsp.status === HTTP.STATUS_SUCCESS) {
            this.data = rsp.result;
            console.log('aaaa', this.data)
        } else {
            let msg = rsp.message;
        }
    });
}

}
