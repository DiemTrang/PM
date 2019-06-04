import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';

import {SignUpComponent} from './sign-up.component';
import {SignUpRoutingModule} from './sign-up-routing.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SignUpRoutingModule, 
    ModalModule.forRoot()
  ],
  declarations: [SignUpComponent]
})
export class SignUpModule { }
