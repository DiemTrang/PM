import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './pages/layout/layout.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', loadChildren: './pages/login/login.module#LoginModule' },
  {
    path: '',
    component: LayoutComponent,
    children:
      [{
        path: 'profile',
        loadChildren: './pages/profile/profile.module#ProfileModule'
      },{
        path: 'project/:_id',
        loadChildren: './pages/project/project.module#ProjectModule'
      },{
        path: 'accounts',
        loadChildren: './pages/accounts/accounts.module#AccountsModule'
      },{
        path: 'task/:_id',
        loadChildren: './pages/task/task.module#TaskModule'
      },{
        path: 'sign-up',
        loadChildren: './pages/sign-up/sign-up.module#SignUpModule'
      },{
        path: 'task-new',
        loadChildren: './pages/task-new/task-new.module#TaskNewModule'
      },{
        path: 'accounts-detail/:_id',
        loadChildren: './pages/accounts-detail/accounts-detail.module#AccountsDetailModule'
      },{
        path: 'project-new',
        loadChildren: './pages/project-new/project-new.module#ProjectNewModule'
      }
      ]
  },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: [
  ]
})
export class AppRoutingModule { 

}
