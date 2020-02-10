import { Component, OnInit } from '@angular/core';
import { AuthService, Credentials } from 'src/app/services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  credentials = {} as Credentials;
  redirectUri: string;

  constructor(
    private authService: AuthService,
    private activateRoute: ActivatedRoute
  ) {
    this.activateRoute.queryParams.subscribe(params => {
      if (params) {
        this.redirectUri = params.redirect_uri;
      }
    });
  }

  ngOnInit() {
    this.authService.checkCredentials('home');
  }

  login() {
    this.authService.login(this.credentials);
  }
}
