import { Component, OnInit } from '@angular/core';
import { TaskProvider } from 'src/app/providers';
import { HTTP } from '../../../app/utilities';
import { Params, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  public data = [];
  public id = 0;

  constructor(
    private tas: TaskProvider,
    private act: ActivatedRoute
  ) { }

  ngOnInit() {
    this.act.params.subscribe((params: Params) => {
      this.id = params["_id"];
      this.getTaskDetail(this.id);
  });
  }

  public getTaskDetail(id: any) {
    this.tas.getTaskDetail(id).subscribe((rsp: any) => {
        
        if (rsp.status === HTTP.STATUS_SUCCESS) {
            this.data = rsp.result;
            console.log('ddd',this.data);
            
        } else {
            let msg = rsp.message;
        }
    });
}

}
