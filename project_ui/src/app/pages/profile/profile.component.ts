import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { AccountProvider } from 'src/app/providers';
import { Token, HTTP } from 'src/app/utilities';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    @ViewChild("changePassModal") public changePassModal: ModalDirective;
    @ViewChild("editProfileModal") public editProfileModal: ModalDirective;

    public show = false;
    public data:any = {};
    public router: Router;

    constructor(router: Router, private pro: AccountProvider, private act: ActivatedRoute) {
        this.router = router;
        this.data = null;
     }

    ngOnInit() {
        this.act.params.subscribe((params: Params) => {
            this.data = null;
            let user = Token.getToken();
            this.getUserInfo(user);
            
        });
    }

    public getUserInfo(id: number) {
        //document.getElementById('preloader').style.display = 'block';

        this.pro.read(id).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result;
                console.log("user Info:", this.data);
                if(this.data.id === 0){
                    alert("Phiên đăng nhập đã hết hạn");
                    this.router.navigate(['/login']);
                }
            }
            else {
                alert("Lỗi đăng nhập");
                this.router.navigate(['/login']);
            }
        }, 
        err => console.log(err));

        
        setTimeout(function () {
            //     document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

    public back() {
        window.history.back();
    }
}
