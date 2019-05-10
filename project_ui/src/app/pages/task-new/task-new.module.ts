import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDatepickerModule } from 'ngx-bootstrap';

import {TaskNewComponent} from './task-new.component';
import {TaskNewRoutingModule} from './task-new-routing.module';

@NgModule({
  imports: [
    CommonModule,
    TaskNewRoutingModule, 
    ModalModule.forRoot(),
    BsDatepickerModule.forRoot()
  ],
  declarations: [TaskNewComponent]
})
export class TaskNewModule { }
