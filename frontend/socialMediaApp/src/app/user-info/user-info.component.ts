import { Component, OnInit } from '@angular/core';
import { UserService} from "../services/user.service";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  name;
  birthday;

  constructor(private userService :UserService) { }

  ngOnInit(): void {
      this.userService.getUserData().subscribe(data => {
        this.name = data[2]['name'];
        this.birthday = data[2]['birthday'];
      })
  }

}
