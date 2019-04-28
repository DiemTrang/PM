import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './pages/layout/layout.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', loadChildren: './pages/login/login.module#LoginModule' },
  { path: 'sign-up', loadChildren: './pages/sign-up/sign-up.module#SignUpModule' },
  {
    path: '',
    component: LayoutComponent,
    children:
        [{
          path: 'profile',
          loadChildren: './pages/profile/profile.module#ProfileModule'
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
export class AppRoutingModule { }
