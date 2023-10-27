import { Component, OnInit, OnDestroy } from '@angular/core';
import { BookService } from '../../service/book.service'
import { IBook,IResponse } from '../../interface/interfaces';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.css']
})

export class BookCardComponent implements OnDestroy {
  subscription: Subscription = new Subscription()
  bookData : IResponse = {} as IResponse
  isVisible : Boolean = false;

  constructor(private bookService : BookService){
    this.subscription = this.bookService.getData().subscribe((data) => {
      // Handle data received from other components
      if(data != null){
        this.bookData = data
        this.isVisible = true;
      }
      else{ console.log("data null") }
      
      console.log('bookCardComponentReceived:', data);
    });
  }
  
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
