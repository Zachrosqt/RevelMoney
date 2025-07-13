import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Transaction {
    id?: string;
    timestamp?: string;
    description?: string;
    amount?: number;
    type?: string;
}

export interface MortgageApplication {
    id?: number;
    appliedAmount?: number;
    interestType?: string;
    interestAmount?: number;
    mortgageStatus?: string;
}

export interface MortgageApplicationRequest extends MortgageApplication {
    mortgageId: number
}

export interface Account {
    id?: number;
    iban?: string;
    balance?: number;
    currency?: string;
    createdAt?: string;
    updatedAt?: string;
    transactions?: Transaction[];
    mortgageApplications? : MortgageApplication[];
}

@Injectable()
export class AccountService {
    private readonly API_URL = 'http://localhost:8081/revelmoney/account';

    constructor(private http: HttpClient) {}

    getInfo(token: any): Observable<Account> {
        return this.http.get<Account>(`${this.API_URL}/info`);
    }
}
