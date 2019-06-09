import { Component, OnInit, ViewChild } from '@angular/core';
import { TaskProvider, AccountProvider } from 'src/app/providers';
import { HTTP, Token, Utils } from 'src/app/utilities';
import { Params, ActivatedRoute } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
    selector: 'app-task',
    templateUrl: './task.component.html',
    styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
    public data: any = {};
    public dataReq: any = {};
    public id = 0;
    public lstStatus: any[] = [];
    public lstAcc: any[] = [];
    public total: number = 0;

    public title1 = '';
    public msgInfo = '';
    public modalState = '';

    public dueDate = '';
    public description = "";
    public attList = [];
    public mesErr = "";

    @ViewChild('discardModal') public discardModal: ModalDirective;
    constructor(
        private tas: TaskProvider,
        private act: ActivatedRoute,
        private acc: AccountProvider
    ) { }

    ngOnInit() {
        this.act.params.subscribe((params: Params) => {
            this.id = params["_id"];
            this.getTaskDetail(this.id);
        });
        let tmpStatus = {
            data: [{
                code: "",
                value: "-- Please Select --"
            }, {
                code: "To Do",
                value: "To Do"
            }, {
                code: "Word In Progress",
                value: "Word In Progress"
            }, {
                code: "In Review",
                value: "In Review"
            }, {
                code: "Verify",
                value: "Verify"
            }, {
                code: "Done",
                value: "Done"
            }]
        }

        this.lstStatus = tmpStatus.data;
        this.searchAcc();
    }

    public getTaskDetail(id: any) {
        this.tas.getTaskDetail(id).subscribe((rsp: any) => {

            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result;
                console.log('ddd', this.data);
                this.dataReq.id = this.data.id;
                
                this.dataReq.decription = this.data.decription;
                this.dataReq.status = this.data.status;
                this.dataReq.dueDate = this.data.dueDate;
                this.dataReq.originalEstimate = this.data.originalEstimate;
            } else {
                let msg = rsp.message;
            }
        });
    }

    public searchAcc() {
        let x = {
            filter: {
            },
            page: 1,
            size: 20,
            sort: [
                {
                    direction: "DESC",
                    field: ""
                }
            ]
        }

        this.acc.search(x).subscribe((rsp: any) => {
            let item = {
                value: "",
                name: "-- Please select --"
            }
            if (rsp.status === HTTP.STATUS_SUCCESS) {

                this.lstAcc = rsp.result.data;
                console.log('lst acc', this.lstAcc);
                this.total = rsp.result.total;
                this.lstAcc.unshift(item);
            }
            else {
                this.lstAcc.unshift(item);
            }
        }, (err) => {
            console.log(err);
        });
    }

    public save() {
        document.getElementById('preloader').style.display = 'block';

        let user = Token.getToken();
        if (user == 0 || user == null) {
            alert("Ban phai Login truoc khi update Task");
            window.location.href = '/login';
            return 0;
        }

        this.dataReq.modifyById = user;
        console.log('updateTask', this.dataReq);

        this.tas.updateTask(this.dataReq).subscribe((rsp: any) => {
            
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.title1 = 'Information';
                this.msgInfo = 'New Task is updated, successfully.';
                this.modalState = 'success';
                this.discardModal.show();
                
                return;
            }
        }, (err) => { console.log(err); });
        

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

}
