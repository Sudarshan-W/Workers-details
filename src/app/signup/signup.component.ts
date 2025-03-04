import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  constructor(private service:HttpService,
              private router:Router
  ){}

    EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    onSubmit(signupForm:NgForm){
      console.log(signupForm.value);
      this.service.registerData(signupForm.value)
      .subscribe((myresponse)=>{
        console.log(myresponse);
        this.router.navigate(['']);
      })
    }  
}
