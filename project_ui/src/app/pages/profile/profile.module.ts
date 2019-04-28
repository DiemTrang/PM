import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';

import {ProfileComponent} from './profile.component';
import {ProfileRoutingModule} from './profile-routing.module';


@NgModule({
  declarations: [
    ProfileComponent
  ],
  imports: [
    CommonModule,
    ProfileRoutingModule, 
    ModalModule.forRoot()
  ]
})
export class ProfileModule { }
