import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import {ProjectComponent} from './project.component';
import {ProjectRoutingModule} from './project-routing.module';
import {BsDatepickerModule } from 'ngx-bootstrap';
import { FormsModule }   from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';

@NgModule({
  declarations: [
    ProjectComponent
  ],
  imports: [
    CommonModule,
    ProjectRoutingModule,
    BsDatepickerModule.forRoot(), 
    FormsModule,
    ModalModule.forRoot(),
    Ng2SmartTableModule
  ]
})
export class ProjectModule { }
