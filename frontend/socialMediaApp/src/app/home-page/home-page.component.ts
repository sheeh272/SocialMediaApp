import { Component, OnInit } from '@angular/core';
import { AuthService} from "../services/auth.service";
import { CookieService } from 'ngx-cookie-service';
import { UserService} from "../services/user.service";
import { PostService} from "../services/post.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  posts=[];

  constructor(private userService: UserService,private authService: AuthService,
  private postService :PostService) { }

  ngOnInit(): void {
    var i; var j;
    this.authService.getUserId().subscribe(id => {
       this.userService.getFriendsList(id).subscribe(friendsList => {
         console.log(friendsList);
         for (i of friendsList){
           this.postService.getPostsOfUser(i).subscribe(userPosts =>{
             for (j of userPosts){
               //this.posts.push(j);
               this.add(this.posts,j);
             }
           });
          console.log(this.posts);
         }
       });
     });
  }

  //this allows posts to be added in order of date created
  add(postList,post){
    let added = false;
    for(let i = 0; i < postList.length; i++){
      if(postList[i]['datePosted'] > post['datePosted']){
        postList.splice(i, 0, post);
        added = true;
        break;
      }
    }
    if(!added){
      postList.push(post);
    }
  }

}
