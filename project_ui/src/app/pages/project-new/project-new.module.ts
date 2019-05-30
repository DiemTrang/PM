import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import {ProjectNewComponent} from './project-new.component';
import {ProjectNewRoutingModule} from './project-new-routing.module';

@NgModule({
  declarations: [
    ProjectNewComponent
  ],
  imports: [
    CommonModule,
    ProjectNewRoutingModule, 
    ModalModule.forRoot()
  ]
})
export class ProjectNewModule { }
