import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.scss']
})
export class LoginpageComponent {

  errorMessage!:string
  loginForm:FormGroup
  constructor(private fb: FormBuilder) {
    this.loginForm=fb.group({
      loginEmail: new FormControl('', [Validators.required, Validators.email]),
      loginPassword: new FormControl('', [Validators.required])
    })
  }

  ngOnInit() {
    
  }
  loginUser() {
    throw new Error('Method not implemented.');
  }
 
}
