import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import { CKEditorModule } from 'ckeditor4-angular';
import { FormsModule } from '@angular/forms';

import {TaskComponent} from './task.component';
import {TaskRoutingModule} from './task-routing.module';

@NgModule({
  imports: [
    CommonModule,
    TaskRoutingModule,
    CKEditorModule, 
    FormsModule,
    ModalModule.forRoot()
  ],
  declarations: [TaskComponent]
})
export class TaskModule { }
