import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { FormGroup, FormBuilder, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountProvider } from '../../../app/providers';
import { HTTP, Token } from '../../../app/utilities';
import { ActivatedRoute } from '@angular/router';

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

    constructor(
        router: Router,
        fb: FormBuilder,
        private act: ActivatedRoute,
        private pro: AccountProvider) {
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
            userName: this.vm.userName,
            password: this.vm.password
        };

        this.pro.signIn(obj).subscribe((rsp: any) => {
            console.log('User: ', rsp);

            if (rsp.status === HTTP.STATUS_SUCCESS) {
                if (rsp.result.role == 'Admin') {
                }
                else if (rsp.result.role == 'Manager') {
                }
                else if (rsp.result.role == 'User') {
                }
                console.log(rsp.result.id);
                
                Token.setToken(rsp.result.id);
                this.router.navigate(['/profile']);
            } else {
                alert("Sai username hoặc Password")
            }
        }, (err) => { console.log(err); });

        setTimeout(function () {
            //document.getElementById('preloader').style.display = 'none';
        }, 500);
    }
}
