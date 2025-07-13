import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({providedIn: 'root'})
export class AuthService {
    private readonly tokenKey = 'tokenKey';
    private readonly API_URL = 'http://localhost:8081/revelmoney/auth';

    constructor(private http: HttpClient, private router: Router) {
    }

    login(username: string, password: string) {
        return this.http.post<{ token: string }>(`${this.API_URL}/login`, {username, password});
    }

    register(data: any) {
        return this.http.post(`${this.API_URL}/register`, data);
    }

    setToken(token: string) {
        localStorage.setItem(this.tokenKey, token);
    }

    getToken(): string | null {
        return localStorage.getItem(this.tokenKey);
    }

    isLoggedIn(): boolean {
        return !!this.getToken();
    }

    logout() {
        localStorage.removeItem(this.tokenKey);
        this.router.navigate(['/login']);
    }

    clearToken() {
        localStorage.removeItem(this.tokenKey);
    }
}
