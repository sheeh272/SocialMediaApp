import { Component, OnInit, Input} from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AuthService} from "../services/auth.service";
import { CookieService } from 'ngx-cookie-service';
import { UserService} from "../services/user.service";

@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.css']
})
export class UserProfilePageComponent implements OnInit {

  name: string;
  birthday: string;

  constructor(private route: ActivatedRoute,private userService: UserService,
  private cookieService: CookieService,private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.getUserId().subscribe(id => {
       this.userService.getUserDataFromId(id).subscribe(data => {
         this.name = data['displayName'];
         this.birthday = data['birthday']
       });
     });
  }

}
