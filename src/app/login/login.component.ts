import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  backendResponse:string='';

  EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

  // PASS_PATTERN = "^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{6,}$";

  constructor(private s:HttpService,
              private router:Router
  ){}

  onSubmit(loginForm: NgForm) {
    this.s.login(loginForm.value).subscribe((response:any) => {
      console.log(response);
      if(response.msg=='valid user'){
        sessionStorage.setItem("username",response.user.name);
        this.router.navigate(['/home'])
        // alert("login success")
      }else{
        this.backendResponse=response.msg;
      }
  })
}

}