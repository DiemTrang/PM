import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';

import {AccountsDetailComponent} from './accounts-detail.component';
import {AccountsDetailRoutingModule} from './accounts-detail-routing.module';
import {BsDatepickerModule } from 'ngx-bootstrap';
import { FormsModule }   from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';

@NgModule({
  imports: [
    CommonModule,
    AccountsDetailRoutingModule, 
    ModalModule.forRoot(),
    BsDatepickerModule.forRoot(), 
    FormsModule,
    Ng2SmartTableModule
  ],
  declarations: [
    AccountsDetailComponent
  ]
})
export class AccountsDetailModule { }
