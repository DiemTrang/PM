import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AccountsDetailComponent } from './accounts-detail.component';

const routes: Routes = [
  {
    path: '',
    component: AccountsDetailComponent
  }
];
@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountsDetailRoutingModule { }
