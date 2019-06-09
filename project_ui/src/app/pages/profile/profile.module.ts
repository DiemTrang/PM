import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';

import {ProfileComponent} from './profile.component';
import {ProfileRoutingModule} from './profile-routing.module';
import {BsDatepickerModule } from 'ngx-bootstrap';
import { FormsModule }   from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';


@NgModule({
  declarations: [
    ProfileComponent
  ],
  imports: [
    CommonModule,
    ProfileRoutingModule, 
    ModalModule.forRoot(),
    BsDatepickerModule.forRoot(), 
    FormsModule,
    ModalModule.forRoot(),
    Ng2SmartTableModule
  ]
})
export class ProfileModule { }
