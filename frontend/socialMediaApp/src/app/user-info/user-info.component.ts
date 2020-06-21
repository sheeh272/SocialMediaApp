import { Component, OnInit, Input} from '@angular/core';
import { UserService} from "../services/user.service";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  //name;
  //birthday;
  @Input() name: string;
  @Input() birthday: string;

  constructor(private userService :UserService) { }

  ngOnInit(): void {
      // this.userService.getUserData().subscribe(data => {
      //   this.name = data[0]['displayName'];
      //   this.birthday = data[0]['birthday'];
      // })
  }

}
