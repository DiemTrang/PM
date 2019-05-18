import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import { RouterModule } from '@angular/router';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {LoginComponent} from './login.component';
import {LoginRoutingModule} from './login-routing.module';


export const routes = [
  { path: '', component: LoginComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    LoginRoutingModule, 
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    ModalModule.forRoot()
  ],
  declarations: [
    LoginComponent
  ]
})
export class LoginModule { }
