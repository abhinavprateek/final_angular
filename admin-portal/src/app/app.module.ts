import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { routing } from './app.routing';

import { AppComponent } from './app.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule, MatToolbarModule, MatGridListModule, MatInputModule,MatSelectModule, MatSlideToggleModule, MatListModule, MatDialogModule} from '@angular/material';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';

import { LoginService } from './service/login.service';
import { AddBookService } from './service/add-book.service';
import { UploadImageService } from './service/upload-image.service';
import { GetBookListService } from './service/get-book-list.service';
import { GetBookService } from './service/get-book.service';
import {EditBookService} from "./service/edit-book.service";
import {RemoveBookService} from "./service/remove-book.service";

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddNewBookComponent } from './components/add-new-book/add-new-book.component';
import { BookListComponent, DialogResultExampleDialog } from './components/book-list/book-list.component';
import { ViewBookComponent } from './components/view-book/view-book.component';
import { EditBookComponent } from './components/edit-book/edit-book.component';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    AddNewBookComponent,
    BookListComponent,
    ViewBookComponent,
    EditBookComponent,
    DialogResultExampleDialog
  ],
  imports: [
    BrowserModule,
	BrowserAnimationsModule,
	MatButtonModule,
	MatCheckboxModule,
	MatToolbarModule,
  HttpModule,
  routing,
  FormsModule,
  MatGridListModule,
  MatInputModule,
  MatSelectModule,
  MatSlideToggleModule,
  MatListModule,
  MatDialogModule
  ],
  providers: [
      LoginService,
      AddBookService,
      UploadImageService,
      GetBookListService,
      GetBookService,
      EditBookService,
      RemoveBookService

  ],
  bootstrap: [AppComponent, DialogResultExampleDialog]
})
export class AppModule { }
