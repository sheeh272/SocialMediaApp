import { Component, OnInit } from '@angular/core';
import { PostService} from "../services/post.service";
import { FormsModule } from '@angular/forms';

interface User {
  id: string;
  name: string;
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
  posts;
  constructor(private postService :PostService) { }

  ngOnInit(): void {
    this.postService.getPostData().subscribe(data => {
    this.posts = data;});
  }

  createPost(event) {
    event.preventDefault()
    console.log(event)
    const target  = event.target;
    const content = target.querySelector('#contents').value;
    console.log(content);
    let user: User = {"id": "e63eb8c3-27ab-4ad1-90ba-888b19302f76","name": "Trap lord gerald","birthday": "1800-07-01"};
    let post: Post = {"author": user, "contents": content};
    let data = JSON.stringify(post);
    console.log(data);
    this.postService.sendPostData(JSON.stringify(post)).subscribe(data => {
    console.log(data);});
  }
}
