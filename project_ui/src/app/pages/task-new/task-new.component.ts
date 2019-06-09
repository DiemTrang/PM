import { Component, OnInit, ViewChild } from '@angular/core';
import { TaskProvider } from 'src/app/providers';
import { HTTP, Token } from 'src/app/utilities';
import { ModalDirective } from 'ngx-bootstrap';
import { ActivatedRoute, Params } from '@angular/router';


@Component({
  selector: 'app-task-new',
  templateUrl: './task-new.component.html',
  styleUrls: ['./task-new.component.css']
})
export class TaskNewComponent implements OnInit {
  public data: any = {};

  public title1 = '';
  public msgInfo = '';
  public modalState = '';

  public dueDate = '';
  public description = "";

  @ViewChild('discardModal') public discardModal: ModalDirective;
  constructor(private pro: TaskProvider, private act: ActivatedRoute, ) { }

  ngOnInit() {

  }

  public create() {
    document.getElementById('preloader').style.display = 'block';

    let user = Token.getToken();
    if(user==0 || user ==null){
      alert("Ban phai Login truoc khi tao Task");
      window.location.href = '/login';
      return 0;
    }

    this.data.modifyBy = user;
    this.data.createdBy = user;
    console.log('hleloooo', this.data);

    this.pro.createTask(this.data).subscribe((rsp: any) => {
      if (rsp.status === HTTP.STATUS_SUCCESS) {
        this.title1 = 'Information';
        this.msgInfo = 'New Task is created, successfully.';
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