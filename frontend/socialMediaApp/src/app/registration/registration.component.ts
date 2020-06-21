import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService} from "../services/user.service";

interface User {
  displayName: string;
  birthday: string;
  loginName: string;
  passcode: string;
}

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private userService :UserService) { }

  ngOnInit(): void {
  }

  register(event){
    event.preventDefault()
    const target  = event.target;
    const username: string = target.querySelector('#username').value;
    const passcode: string = target.querySelector('#passcode').value;
    const displayName: string = target.querySelector('#displayName').value;
    const birthday: string = target.querySelector('#birthday').value;
    let user: User = {"displayName": displayName, "birthday": birthday,
    "loginName" : username, "passcode": passcode};
    console.log(user);
    this.userService.registerUser(JSON.stringify(user)).subscribe(data => {
    console.log(data);});
  }

}
