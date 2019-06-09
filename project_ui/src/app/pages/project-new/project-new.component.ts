import { Component, OnInit, ViewChild } from '@angular/core';
import { ProjectProvider, AccountProvider } from 'src/app/providers';
import { HTTP } from 'src/app/utilities';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-project-new',
  templateUrl: './project-new.component.html',
  styleUrls: ['./project-new.component.css']
})
export class ProjectNewComponent implements OnInit {
  public data: any = {};

  public title = '';
  public msgInfo = '';
  public modalState = '';
  public userId = 0;

  @ViewChild('discardModal') public discardModal: ModalDirective;
  constructor(
    private pro: ProjectProvider,
    private act: ActivatedRoute,
    private rou: Router,
    private acc: AccountProvider) { }

  ngOnInit() {
    this.getUserId;
  }

  public getUserId() {
    this.acc.getUserId(1).subscribe((rsp: any) => {
      if (rsp.status === HTTP.STATUS_SUCCESS) {
        console.log('userId', rsp.result);
        this.userId = rsp.result;
        return;
      }
    }, (err) => { console.log(err); });
  }


  public create() {
    document.getElementById('preloader').style.display = 'block';
    this.pro.createProject(this.data).subscribe((rsp: any) => {
      if (rsp.status === HTTP.STATUS_SUCCESS) {
        this.title = 'Information';
        this.msgInfo = 'New Project is created, successfully.';
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
