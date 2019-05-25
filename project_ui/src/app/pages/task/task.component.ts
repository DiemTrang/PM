import { Component, OnInit } from '@angular/core';
import { TaskProvider } from 'src/app/providers';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  constructor(
    private tas: TaskProvider
  ) { }

  ngOnInit() {
  }

}
