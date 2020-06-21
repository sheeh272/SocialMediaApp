import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { UserProfilePageComponent } from './user-profile-page/user-profile-page.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  { path: 'main', component: UserProfilePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
