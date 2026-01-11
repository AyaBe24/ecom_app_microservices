import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ChatService {
    private apiUrl = 'http://localhost:8080/chat'; // Assuming bot service runs on 8080


    constructor(private http: HttpClient) { }

    ask(query: string): Observable<string> {
        return this.http.get(this.apiUrl, {
            params: { query },
            responseType: 'text'
        });
    }
}
