import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService} from "../services/user.service";
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AuthService} from "../services/auth.service";
//import { User} from "../model/GlobalInterfaces"

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  posts;

  constructor(private userService :UserService,private route: ActivatedRoute,
    private router: Router, private cookieService: CookieService,
    private authService: AuthService) { }

  ngOnInit(): void {}

  login(event) {
    event.preventDefault()
    const target  = event.target;
    const username = target.querySelector('#username').value;
    const passcode = target.querySelector('#passcode').value;

    this.authService.getToken(username,passcode).subscribe(data => {
      if(data){
        this.cookieService.set("Jwt",data['token']);
        this.router.navigate(['/main']);
        //this.router.navigate(['/home']);
      }
      else {
        alert("UserName or passcode not found");
      }});
  }

}
