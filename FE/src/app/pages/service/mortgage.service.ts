import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MortgageApplicationRequest } from './account.service';

export interface Mortgage {
    id?: number;
    name?: string;
    description?: string;
    maxAmount?: number;
    maxLtvRatio?: number;
    validFrom?: string;
    validTo?: string;
    interestAmount?: number;
}

@Injectable({ providedIn: 'root' })
export class MortgageService {
    private readonly API_URL = 'http://localhost:8081/revelmoney/mortgage';

    constructor(private http: HttpClient) {}

    getList(): Observable<Mortgage[]> {
        return this.http.get<Mortgage[]>(`${this.API_URL}/list`);
    }

    applyForMortgage(mortgageApplicationRequest: MortgageApplicationRequest): Observable<any> {
        return this.http.post<any>(`${this.API_URL}/apply`, mortgageApplicationRequest);
    }
}
