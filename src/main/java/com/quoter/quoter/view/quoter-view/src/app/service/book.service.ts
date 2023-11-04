import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IBook,IResponse } from '../interface/interfaces';
import { BehaviorSubject,Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class BookService {
    private apiUrl = 'http://localhost:8080/api/book'; // Replace with your API endpoint

    private dataSubject = new Subject<any>()
    
    //currentBook = this.bookSource.asObservable();
    private eventSubject = new BehaviorSubject<any>(undefined);
    private currentBook : IBook = {} as IBook
    constructor(private http: HttpClient) { }

    getLine(range: number, urlFormat:string){
       const params = new HttpParams()
            .set('range', range.toString())
            .set('urlFormat', urlFormat);

          // Make the GET request with the parameters
          //responsedan kitabı book'a mapleyip currentbook kuyruğuna paslayacağım
          this.http.get<IBook>(this.apiUrl, { params }).subscribe(
            {
              next: (result) => this.dataSubject.next(result),
              error: (err) => console.error(err),
              complete: () => console.info('complete')
            }
          )
    }

    getCurrentBook() : IBook {
      return this.currentBook
    }

    sendData(data: any) {
      this.dataSubject.next(data);
    }

    getData() {
      return this.dataSubject.asObservable();
    }
}
