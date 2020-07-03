import { Component, OnInit, Input} from '@angular/core';
import { PostService} from "../services/post.service";
import { UserService} from "../services/user.service"
import { FormsModule } from '@angular/forms';
import { AuthService} from "../services/auth.service";


interface User {
  id: string;
  displayName: string;
  birthday: string;
}

interface Post {
  author: User
  contents: string
}

@Component({
  selector: 'app-post-time-line',
  templateUrl: './post-time-line.component.html',
  styleUrls: ['./post-time-line.component.css']
})
export class PostTimeLineComponent implements OnInit {
  @Input() posts;
  //@Input() name;
  //@Input() birthday;

  user: User = {"id": "", "displayName": "", "birthday": ""};

  constructor(private postService: PostService, private userService: UserService,
  private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.getUserId().subscribe(id => {
       this.userService.getUserDataFromId(id).subscribe(data => {
         this.user.id = data['id']
         this.user.displayName = data['displayName'];
         this.user.birthday = data['birthday'];
       });

     });
  }

  createPost(event) {
    event.preventDefault()
    console.log(event)
    const target  = event.target;
    const content = target.querySelector('#contents').value;
    console.log(content);
    //let user: User = {"id" : "24da2cf9-4511-4a39-9883-8ec42777bc92", "displayName": this.name, "birthday": this.birthday};
    let post: Post = {"author": this.user, "contents": content};
    let data = JSON.stringify(post);
    console.log(data);
    this.postService.sendPostData(JSON.stringify(post)).subscribe(data => {
    console.log(data);});
  }
}
