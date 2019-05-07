import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';

import {TaskComponent} from './task.component';
import {TaskRoutingModule} from './task-routing.module';

@NgModule({
  imports: [
    CommonModule,
    TaskRoutingModule, 
    ModalModule.forRoot()
  ],
  declarations: [TaskComponent]
})
export class TaskModule { }
