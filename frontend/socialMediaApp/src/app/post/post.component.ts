import { Component, OnInit, Input} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  //posts;
  @Input() author: string;
  @Input() contents: string;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {}
}
