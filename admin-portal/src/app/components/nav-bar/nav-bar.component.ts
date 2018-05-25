import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

	private loggedIn = false;

  constructor(private loginService: LoginService, private router:Router) { }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        console.log("Session checing for navigation menu.....");
        this.loggedIn=true;
      },
      error => {
        this.loggedIn=false;
      }
    );
  }

  logout(){

    console.log("LOGOUT HAS BEEN HIT........");
    this.loginService.logout().subscribe(
      res => {
        console.log(res);
        location.reload();
      },
      error => {
        console.log(error);
      }
    );

    this.router.navigate(['/']);

  }


}
