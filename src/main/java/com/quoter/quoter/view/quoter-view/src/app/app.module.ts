import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookCardComponent } from './book-card/book-card/book-card.component';
import { UserInputComponent } from './user-input/user-input/user-input.component';
import { HttpClient } from '@angular/common/http'

@NgModule({
  declarations: [
    AppComponent,
    BookCardComponent,
    UserInputComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClient
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
