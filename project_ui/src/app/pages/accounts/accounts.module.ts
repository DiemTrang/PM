import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';

import {AccountsComponent} from './accounts.component';
import {AccountsRoutingModule} from './Accounts-routing.module';
import {BsDatepickerModule } from 'ngx-bootstrap';
import { FormsModule }   from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';

@NgModule({
  imports: [
    CommonModule,
    AccountsRoutingModule, 
    ModalModule.forRoot(),
    BsDatepickerModule.forRoot(), 
    FormsModule,
    Ng2SmartTableModule
  ],
  declarations: [
    AccountsComponent
  ]
})
export class AccountsModule { }
