import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDatepickerModule } from 'ngx-bootstrap';
import { CKEditorModule } from 'ckeditor4-angular';

import {TaskNewComponent} from './task-new.component';
import {TaskNewRoutingModule} from './task-new-routing.module';
import { FormsModule } from '@angular/forms';
import { NgxUploaderModule } from 'ngx-uploader';

@NgModule({
  imports: [
    CommonModule,
    TaskNewRoutingModule,
    CKEditorModule,
    ModalModule.forRoot(),
    BsDatepickerModule.forRoot(),
    FormsModule,
    NgxUploaderModule
  ],
  declarations: [TaskNewComponent]
})
export class TaskNewModule { }
