import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Book } from '../models/book';


@Injectable()
export class GetBookListService {

  constructor(private http: Http) { }


  getBookList(){

    let url = "http://localhost:8888/book/bookList";
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.get(url, {headers: headers});
    //return this.http.post(url, JSON.stringify(book), {headers: headers});


  }

}
