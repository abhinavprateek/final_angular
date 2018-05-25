import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class RemoveBookService {

  constructor(private http: Http) { }

  sendBook(bookId: number){

    let url = "http://localhost:8888/book/remove";
    //let url = "http://localhost:8181/tokens";
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, bookId, {headers: headers});

  }

}
