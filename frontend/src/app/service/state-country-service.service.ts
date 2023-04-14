import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Country } from '../model/country';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { State } from '../model/state';
@Injectable({
  providedIn: 'root',
})
export class StateCountryServiceService {
  // ng g s state
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}

  public getAllCountries(): Observable<Country[]> {
    return this.http
      .get<Country[]>(`${this.apiServerUrl}/api/countries`)
      .pipe(map((response: Country[]) => response));
  }

  public getAllStates(): Observable<State[]> {
    return this.http
      .get<State[]>(`${this.apiServerUrl}/api/states`)
      .pipe(map((response: State[]) => response));
  }

  public getStatesByCode(code: any): Observable<State[]> {
    return this.http
      .get<State[]>(`${this.apiServerUrl}/api/statescode?code=${code}`)
      .pipe(map((response: State[]) => response));
  }
}
