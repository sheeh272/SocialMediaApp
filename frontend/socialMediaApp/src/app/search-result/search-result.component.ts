import { Component, OnInit, Input} from '@angular/core';
import { UserService} from "../services/user.service"

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {
  @Input() name: String;
  @Input() image: Blob;
  @Input() otherUsersId: string;
  @Input() id: string;

  url;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    var reader = new FileReader();
    reader.readAsDataURL(this.image);
    this.url = reader.result;
    reader.onload = (_event) => {
      this.url = reader.result;
    }

  }

  addFriend(){
    this.userService.addFriend(this.otherUsersId,this.id).subscribe(data => {
        if(data){
          alert("added friend Succesfully");
        }
        else{
          alert("something went wrong");
        }
    });
  }

}
