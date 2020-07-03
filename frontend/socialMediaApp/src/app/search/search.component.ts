import { Component, OnInit } from '@angular/core';
import { UserService} from "../services/user.service"
import { ImageService} from "../services/image.service"
import { AuthService} from "../services/auth.service"
import { ActivatedRoute } from '@angular/router'

interface User {
  name: string;
  image: Blob;
  id: String;
}

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private userService: UserService,private imageService: ImageService,
  private authService: AuthService) {}

  users = []
  id;
  searchInitiated;

  ngOnInit(): void {
    this.authService.getUserId().subscribe(id=>{
      this.id = id;
    });
  }


  searchUser(event) {
    const target  = event.target;
    const content = target.querySelector('#contents').value;
    this.users = [];
    var userData;
    this.userService.getUserFromName(content).subscribe(userList=>{
      for (userData of userList){
           console.log(userData['id']);
           console.log(userData['loginName']);
           let user:User = {'name':userData['displayName'],'image':null, 'id':userData['id']}
           this.imageService.getProfilePictureByName(userData['loginName']).subscribe(image=>{
             user.image = image;
             this.users.push(user)
           });
           this.searchInitiated = true;
      }
    });
  }

}
