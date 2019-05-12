import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TaskComponent } from './task.component';

const routes: Routes = [
  {
    path: '',
    component: TaskComponent
  }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TaskRoutingModule { }