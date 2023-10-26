import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { BookService } from '../../service/book.service'

@Component({
  selector: 'app-user-input',
  templateUrl: './user-input.component.html',
  styleUrls: ['./user-input.component.css']
})

export class UserInputComponent {
  data: any
  range : number = 0
  urlFormat = "text/plain"

  constructor(private bookService : BookService) {}

  sendData() {
    console.log('sending data to server range:' + this.range)
    this.bookService.getLine(this.range, this.urlFormat)
  }

}
