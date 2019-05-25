import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';

import {AccountsDetailComponent} from './accounts-detail.component';
import {AccountsDetailRoutingModule} from './accounts-detail-routing.module';

@NgModule({
  imports: [
    CommonModule,
    AccountsDetailRoutingModule, 
    ModalModule.forRoot()
  ],
  declarations: [
    AccountsDetailComponent
  ]
})
export class AccountsDetailModule { }
