import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDatepickerModule } from 'ngx-bootstrap';
import { CKEditorModule } from 'ckeditor4-angular';

import {TaskNewComponent} from './task-new.component';
import {TaskNewRoutingModule} from './task-new-routing.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    TaskNewRoutingModule,
    CKEditorModule,
    ModalModule.forRoot(),
    BsDatepickerModule.forRoot(),
    FormsModule,
  ],
  declarations: [TaskNewComponent]
})
export class TaskNewModule { }
