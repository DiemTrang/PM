import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProjectNewComponent } from './project-new.component';

const routes: Routes = [
  {
    path: '',
    component: ProjectNewComponent
  }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectNewRoutingModule { }
