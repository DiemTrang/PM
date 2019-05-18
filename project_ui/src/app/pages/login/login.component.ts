import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { FormGroup, FormBuilder, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public router: Router;
  public form: FormGroup;
  public email: AbstractControl;
  public password: AbstractControl;   
  public vm: any = { email: "", userName: "", password: "" }

  @ViewChild("forgotPassModal") public forgotPassModal: ModalDirective;

  public show = false;

  constructor(router: Router, fb: FormBuilder) {
    this.router = router;
    this.form = fb.group({
      'email': 'hoan',
      'password': '123'
    });

    this.email = this.form.controls['userName'];
    this.password = this.form.controls['password'];
  }

  ngOnInit() {
  }

  public onSubmit(): void {
    if (this.form.valid) {
      alert("Hello");
      this.router.navigate(['/profile']);
    }
    else {
      alert('Sai Username hoặc Password');
    }
  }

  public signIn() {
    //document.getElementById('preloader').style.display = 'block';


    let obj = {
        email: this.vm.userName,
        password: this.vm.password
    };
    if(this.vm.userName == 'hoan' && this.vm.password == '123'){
      this.router.navigate(['/profile']);
    }
    else {
      alert("Dẹp me di");
      alert("Đùa thôi. Username: Hoan, Password: 123");
    }
    // this.pro.signIn(obj).subscribe((rsp: any) => {
    //     if (rsp.status === HTTP.STATUS_SUCCESS) {
    //         this.message = "";
    //         this.pro.saveAuth(rsp.result);
    //     } else {
    //         this.showMsg = true;
    //         this.message = " Invalid User ID or Password";
    //     }
    //     this.loader = false;
    // }, (err) => { console.log(err); });

    setTimeout(function () {
        //document.getElementById('preloader').style.display = 'none';
    }, 500);
}
}
