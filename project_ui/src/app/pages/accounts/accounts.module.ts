import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';

import {AccountsComponent} from './accounts.component';
import {AccountsRoutingModule} from './Accounts-routing.module';

@NgModule({
  imports: [
    CommonModule,
    AccountsRoutingModule, 
    ModalModule.forRoot()
  ],
  declarations: [
    AccountsComponent
  ]
})
export class AccountsModule { }
