import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  public message = "";
  public pwdPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$$";
  public fullName = '';

  @ViewChild("infoModal") public infoModal: ModalDirective;

  constructor(private act: ActivatedRoute ) { }

  ngOnInit() {
    this.act.params.subscribe((params: Params) => {
      this.fullName = "";
    }
  )};

  public save(valid: boolean) {
  }

}
