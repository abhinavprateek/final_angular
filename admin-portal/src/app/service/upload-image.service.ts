import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class UploadImageService {

  filesToUpload: Array<File>;

  constructor(private http: Http) {
      this.filesToUpload =[];
   }

   fileChangeEvent(fileInput: any){
        this.filesToUpload = <Array<File>> fileInput.target.files;
   }

   upload(bookId: number){
     let formData: any = new FormData();
     let headers = new Headers ({
       'x-auth-token' : localStorage.getItem('xAuthToken')
     });
     if(this.filesToUpload.length>0){
        let file = this.filesToUpload[0];
        formData.append("file", file, file.name);
        this.http.post("http://localhost:8888/book/add/image?id="+bookId, formData, {headers}).subscribe(
          res => {
            console.log(res);
          },
          err => {
            console.log(err)
          }
        );
     }

   }


  modify(bookId: number) {
    let formData: any = new FormData();
    let headers = new Headers ({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    if(this.filesToUpload.length>0){
       let file = this.filesToUpload[0];
       formData.append("file", file, file.name);
       this.http.post("http://localhost:8888/book/add/image?id="+bookId, formData, {headers}).subscribe(
         res => {
           console.log(res);
         },
         err => {
           console.log(err)
         }
       );
    }
  }


}
