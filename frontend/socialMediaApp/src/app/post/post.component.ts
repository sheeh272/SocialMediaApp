import { Component, OnInit, Input} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PostService} from "../services/post.service";

interface Post {
  author: User
  contents: string
}

interface User {
  id: string;
  displayName: string;
  birthday: string;
}

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  //posts;
  @Input() author: string;
  @Input() contents: string;
  @Input() isAuthor: boolean;
  @Input() postId: string;
  @Input() user;

  constructor(private http: HttpClient, private postService: PostService) { }

  ngOnInit(): void {
    //console.log(this.postId);
  }

  deletePost(){
    this.postService.deletePost(this.postId).subscribe(data => {
        if(!data) {
          alert("error deleting post");
        }
    });
  }

  editPost(){
    //document.getElementById('editAlert').style.display = "block";
      document.getElementById(this.postId).style.display = "block";
  }

  submitEdit(event){
    const target  = event.target;
    //const content = target.querySelector('#editContents').value;
    const content = target.querySelector('#editContents').value;
    let post: Post = {"author": this.user, "contents": content};
    let data = JSON.stringify(post);
    //document.getElementById('editAlert').style.display = "none";
    document.getElementById(this.postId).style.display = "none";
    this.postService.editPost(this.postId, JSON.stringify(post)).subscribe(data => {
      console.log(data);});
  }

  exitEdit(event){
      document.getElementById('editAlert').style.display = "none";
  }

}
