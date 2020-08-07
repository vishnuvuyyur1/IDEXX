import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, BehaviorSubject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private results = new BehaviorSubject(null);
  currentResults = this.results.asObservable();
  constructor(private http: HttpClient) { }


  setResults(data: any) {
    this.results.next(data);
  }

  getResults(): any {
    return this.currentResults;
  }

  getBookAndAlbum(term: string) {
    let params = new HttpParams();
    params = params.append('term', term);
    return this.http.get<any>(`${environment.apiUrl}/combi`, { params: params });
  }
}
