import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @ViewChild("forgotPassModal") public forgotPassModal: ModalDirective;
  
  public show = false;

  constructor() { }

  ngOnInit() {
  }

}
