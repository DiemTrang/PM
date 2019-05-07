import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @ViewChild("changePassModal") public changePassModal: ModalDirective;

  public show = false;

  constructor() { }

  ngOnInit() {
  }

  public back() {
    window.history.back();
  }
}
