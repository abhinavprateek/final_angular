import { Component, OnInit } from '@angular/core';
import { AddBookService } from '../../service/add-book.service';
import { Book } from '../../models/book';
import { UploadImageService } from '../../service/upload-image.service';

@Component({
  selector: 'app-add-new-book',
  templateUrl: './add-new-book.component.html',
  styleUrls: ['./add-new-book.component.css']
})
export class AddNewBookComponent implements OnInit {

  //private newBook: Book = new Book();
  private newBook: Book;
  private bookAdded: boolean = false;

  constructor(private addBookService: AddBookService, private uploadImageService: UploadImageService) { }

  ngOnInit() {
    this.initializeBookParams(false);
  }

  initializeBookParams(bookAdded: boolean){
    this.bookAdded=bookAdded;
    this.newBook = new Book();
    this.newBook.active=true;
    this.newBook.category="Management";
    this.newBook.language="english";
    this.newBook.format="paperback";
  }

  onSubmit(){

      this.addBookService.sendBook(this.newBook).subscribe(
            data => {
              console.log(data);
              this.uploadImageService.upload(JSON.parse(JSON.parse(JSON.stringify(data))._body).id);
              this.initializeBookParams(true);
              /*
              this.uploadImageService.upload(JSON.parse(JSON.parse(JSON.stringify(data))._body).id);
              this.bookAdded = true;
              this.newBook = new Book();
              this.newBook.active=true;
              this.newBook.category="Management";
              this.newBook.language="english";
              this.newBook.format="paperback";
              */

            },
            error => {

            }

      );

  }


}
