import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { AccountProvider } from 'src/app/providers';
import { Token, HTTP } from 'src/app/utilities';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    @ViewChild("changePassModal") public changePassModal: ModalDirective;
    @ViewChild("editProfileModal") public editProfileModal: ModalDirective;

    public show = false;
    public data = [];

    constructor(private pro: AccountProvider) { }

    ngOnInit() {
        let user = Token.getToken();
        console.log('aaaaaa', user);
        
        this.getUserInfo(user);
    }

    public getUserInfo(id: number) {
        //document.getElementById('preloader').style.display = 'block';

        this.pro.read(id).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result.data;
                console.log("user Info:", this.data);
                
            } else {

            }
        }, err => console.log(err));

        // setTimeout(function () {
        //     document.getElementById('preloader').style.display = 'none';
        // }, 500);
    } 

    public back() {
        window.history.back();
    }
}
