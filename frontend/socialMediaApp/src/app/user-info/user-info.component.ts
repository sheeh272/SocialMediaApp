import { Component, OnInit, Input} from '@angular/core';
import { UserService} from "../services/user.service";
import { FormsModule } from '@angular/forms';
import { ImageService} from "../services/image.service";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  //name;
  //birthday;
  url;
	msg = "";
  @Input() name: string;
  @Input() birthday: string;

  constructor(private userService :UserService, private imageService :ImageService) { }

  ngOnInit(): void {
    this.imageService.getProfilePicture().subscribe(image => {
        //this.url = image
        var reader = new FileReader();
    		reader.readAsDataURL(image);
        this.url = reader.result;
    		reader.onload = (_event) => {
    			this.msg = "";
    			this.url = reader.result;
    		}
    });
  }

  //citation for code snippet below
  //Soumitra Roy
  //May 7th 2020
  //How to upload and display image using Angular 8
  //version 1
  //https://www.roytuts.com/how-to-upload-and-display-image-using-angular/
  selectFile(event) {
  		if(!event.target.files[0] || event.target.files[0].length == 0) {
  			this.msg = 'You must select an image';
  			return;
  		}

  		var mimeType = event.target.files[0].type;

  		if (mimeType.match(/image\/*/) == null) {
  			this.msg = "Only images are supported";
  			return;
  		}

      //event.target.files[0].reset();
  		var reader = new FileReader();
  		reader.readAsDataURL(event.target.files[0]);

  		reader.onload = (_event) => {
  			this.msg = "";
  			this.url = reader.result;
        //below is modification to code snippet since I need to transfer file to my backend
        this.imageService.uploadImage(event.target.files[0]).subscribe(data => {
          console.log(data);
         });
  		}
  	}

}
