import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import {AccountProvider} from '../../../app/providers';
import { HTTP, Utils, Token, AccessRight, AttachmentType } from '../../../app/utilities';


@Component({
    selector: 'app-accounts',
    templateUrl: './accounts.component.html',
    styleUrls: ['./accounts.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class AccountsComponent implements OnInit {
    @ViewChild("editAccountModal") public editAccountModal: ModalDirective;

    public show = false;
    public lstAcc = [];

    constructor(private pro: AccountProvider) {
    }

    ngOnInit() {
    }

    public searchAccount() {
        document.getElementById('preloader').style.display = 'block';

        this.pro.search("").subscribe((rsp: any) => {
            let item = {
                sfId: "",
                clientAccount: "-- Please select --"
            }
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                let tam = [];

                rsp.result.data.forEach(element => {
                    if (element.accountType != 'Import') {
                        tam.push(element);
                    }
                });
                rsp.result.data = tam;
                rsp.result.data.unshift(item);
                this.lstAcc = rsp.result.data;
            }
            else {
                this.lstAcc.unshift(item);
            }
        }, (err) => {
            console.log(err);
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

}
