import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import {ProjectNewComponent} from './project-new.component';
import {ProjectNewRoutingModule} from './project-new-routing.module';
import { FormsModule }   from '@angular/forms';

@NgModule({
  declarations: [
    ProjectNewComponent
  ],
  imports: [
    CommonModule,
    ProjectNewRoutingModule, 
    ModalModule.forRoot(),
    FormsModule
  ]
})
export class ProjectNewModule { }
