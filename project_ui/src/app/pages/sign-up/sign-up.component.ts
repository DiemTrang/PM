import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { ActivatedRoute, Params } from '@angular/router';
import { AccountProvider } from 'src/app/providers';
import { HTTP } from 'src/app/utilities';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  public data: any = {};
  public title = '';
  public msgInfo = '';
  public modalState = '';
  public message = "";
  public pwdPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$$";
  public fullName = '';

  @ViewChild('discardModal') public discardModal: ModalDirective;

  constructor(
    private act: ActivatedRoute,
    private acc: AccountProvider) { }

  ngOnInit() {
    this.act.params.subscribe((params: Params) => {
      this.fullName = "";
    }
    )
  };

  public create() {
    document.getElementById('preloader').style.display = 'block';
    this.acc.create(this.data).subscribe((rsp: any) => {
      if (rsp.status === HTTP.STATUS_SUCCESS) {
        this.title = 'Information';
        this.msgInfo = 'New User is created, successfully.';
        this.modalState = 'success';
        this.discardModal.show();
        return;
      }
      else if (rsp.message != '' || rsp.message != null) {
        console.log('hhhhhh', rsp);
        this.title = 'Information';
        this.msgInfo = rsp.message;
        this.modalState = 'success';
        this.discardModal.show();
        return;
      }
    }, (err) => { console.log(err); });

    setTimeout(function () {
      document.getElementById('preloader').style.display = 'none';
    }, 500);
  }

}
