import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  @ViewChild("editAccountModal") public editAccountModal: ModalDirective;

  public show = false;

  constructor() { }

  ngOnInit() {
  }

}
