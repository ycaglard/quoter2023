import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class BookService {
   private apiUrl = 'http://localhost:8080/api/book'; // Replace with your API endpoint

    constructor(private http: HttpClient) { }

    getLine(range: number, urlFormat:string): Observable<any> {
       const params = new HttpParams()
            .set('param1', range)
            .set('param2', urlFormat);

          // Make the GET request with the parameters
          return this.http.get(this.apiUrl, { params });
    }
}
