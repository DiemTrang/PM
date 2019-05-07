import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TaskNewComponent } from './task-new.component';

const routes: Routes = [
  {
    path: '',
    component: TaskNewComponent
  }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TaskNewRoutingModule { }
