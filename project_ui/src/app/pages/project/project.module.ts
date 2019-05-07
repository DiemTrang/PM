import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import {ProjectComponent} from './project.component';
import {ProjectRoutingModule} from './project-routing.module';

@NgModule({
  declarations: [
    ProjectComponent
  ],
  imports: [
    CommonModule,
    ProjectRoutingModule, 
    ModalModule.forRoot()
  ]
})
export class ProjectModule { }
