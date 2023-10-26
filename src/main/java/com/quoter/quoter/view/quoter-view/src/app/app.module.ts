import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookCardComponent } from './book-card/book-card/book-card.component';
import { UserInputComponent } from './user-input/user-input/user-input.component';
import { HttpClientModule } from '@angular/common/http'
import { BookService } from './service/book.service'

@NgModule({
  declarations: [
    AppComponent,
    BookCardComponent,
    UserInputComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    BookService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
