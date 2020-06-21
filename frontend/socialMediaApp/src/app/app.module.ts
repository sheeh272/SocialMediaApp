import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserInfoComponent } from './user-info/user-info.component';
import {HttpClientModule} from '@angular/common/http';
import { PostComponent } from './post/post.component';
import { PostTimeLineComponent } from './post-time-line/post-time-line.component';
import {  PostService} from "./services/post.service";
import {  UserService} from "./services/user.service";
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { UserProfilePageComponent } from './user-profile-page/user-profile-page.component';
import { RegistrationComponent } from './registration/registration.component';
import { CookieService } from 'ngx-cookie-service';
import {  AuthService} from "./services/auth.service";

@NgModule({
  declarations: [
    AppComponent,
    UserInfoComponent,
    PostComponent,
    PostTimeLineComponent,
    LoginComponent,
    UserProfilePageComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [PostService,UserService,AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
