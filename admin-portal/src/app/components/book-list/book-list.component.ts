import { Component, OnInit } from '@angular/core';
import { Book } from '../../models/book';
import { Router } from '@angular/router';
import { LoginService } from '../../service/login.service';
import { GetBookListService } from '../../service/get-book-list.service';
import { RemoveBookService } from '../../service/remove-book.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { forkJoin } from "rxjs/observable/forkJoin";


@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  private selectedBook : Book;
  private checked: boolean;
  private bookList: Book [];
  private allChecked: boolean;
  private removeBookList: Book[] = new Array();

  constructor(private getBookListService: GetBookListService, private router: Router, public dialog: MatDialog, private removeBookService: RemoveBookService) { }

  ngOnInit() {
    this.getBookListService.getBookList().subscribe(
      res =>{
          this.bookList = res.json();
          console.log(this.bookList);
      },
      error => {
          console.log(error);

      }
    );

  }

  onSelect(book: Book){
      this.selectedBook = book;
      this.router.navigate(['/viewBook', this.selectedBook.id ]);
  }

  updateRemoveBookList(checked: Boolean, book: Book){
        if(checked){
            this.removeBookList.push(book);
        }
        else{
            this.removeBookList.splice(this.removeBookList.indexOf(book),1);
        }

  }

  removeSelectedBooks(){

    let allPromises = [];
    let dialogRef = this.dialog.open(DialogResultExampleDialog);
    dialogRef.afterClosed().subscribe(
      result => {
        console.log(result);
        if(result == "yes"){

          for(let i=0;i<this.removeBookList.length;i++){
              let currentBook = this.removeBookList[i];
              allPromises.push(this.removeBookService.sendBook(currentBook.id));
          }

          forkJoin(allPromises).subscribe(results => {
              location.reload();
          });

        }

      }

    );

  }

  updateSelected(checked:Boolean){
      if(checked){
        this.allChecked = true;
        this.removeBookList = this.bookList.slice();
      }
      else{
        this.allChecked = false;
        this.removeBookList = [];
      }
  }

  openDialog(book: Book){
    let dialogRef = this.dialog.open(DialogResultExampleDialog);
    dialogRef.afterClosed().subscribe(
      result => {
        console.log(result);
        if(result == "yes"){
            this.removeBookService.sendBook(book.id).subscribe(
              res => {
                  console.log(res);
                  location.reload();
              },
              err => {
                  console.log(err);
              }
            );
        }

      }

    );

  }

}


@Component({
  selector: 'dialog-result-example-dialog',
  templateUrl: './dialog-result-example-dialog.html'
})

export class DialogResultExampleDialog {

   constructor(public dialogRef: MatDialogRef<DialogResultExampleDialog>) {}

}
