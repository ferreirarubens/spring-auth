import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpHeaders,
  HttpRequest,
  HttpParams
} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

export interface Credentials {
  username: string;
  password: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private cookieService: CookieService
  ) {}

  login(credentials: Credentials) {
    const params = new HttpParams()
      .set('username', credentials.username)
      .set('password', credentials.password)
      .set('grant_type', 'password');
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      Authorization: 'Basic ' + btoa('coderef:secret')
    });

    return this.httpClient
      .post<any>(environment.oauth + '?' + params.toString(), null, {
        headers
      })
      .toPromise()
      .then(data => this.saveToken(data))
      .catch(err => alert('Invalid Credentials'));
  }

  saveToken(token) {
    const expireDate = new Date().getTime() + 1000 * token.expires_in;
    this.cookieService.set('access_token', token.access_token, expireDate);
    console.log('Obtained Access token');
    this.router.navigate(['/']);
  }

  checkCredentials(url: 'home' | 'login') {
    if (!this.cookieService.check('access_token')) {
      if (url === 'home') {
        this.router.navigate(['/login']);
      }
    } else {

    }
  }

  logout() {
    this.cookieService.delete('access_token');
    this.router.navigate(['/login']);
  }
}
