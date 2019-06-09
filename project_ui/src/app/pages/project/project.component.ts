import { Component, OnInit } from '@angular/core';
import { TaskProvider, ProjectProvider, AccountProvider } from 'src/app/providers';
import { HTTP } from '../../../app/utilities';
import { Params, ActivatedRoute } from '@angular/router';


@Component({
    selector: 'app-project',
    templateUrl: './project.component.html',
    styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
    public show = false;
    public lstPro = [];
    public pageSize = 10;
    public dataTask = [];
    public data = [];
    public total: number = 0;
    public pager: any = {};
    public pagedItems: any[];
    public id = 0;
    public test = "img4.jpg";
    public lstAcc: any[] = [];
    public lstStatus: any[] = [];
    public taskStatus = "";
    public taskAssign = null;
    public taskName = "";
    public curentPage = 1;
    public userId = 0;

    public settings = {
        selectMode: 'single',  //single|multi
        hideHeader: false,
        hideSubHeader: false,
        actions: {
            add: false,
            edit: false,
            delete: false,
            custom: [],
        },
        handle: {
            editable: false
        },
        noDataMessage: 'No data found',
        columns: {
            id: {
                title: 'Task',
                filter: false,
                type: 'html',
                valuePrepareFunction: (cell, row) => {
                    return `<a href="/task/${row.id}">${row.id}</a>`
                },
            },
            project: {
                title: 'Project',
                type: 'string',
                filter: false
            },
            taskName: {
                title: 'Name',
                type: 'string',
                filter: false
            },
            status: {
                title: 'Word Flow',
                type: 'string',
                filter: false
            },
            asign: {
                title: 'Asgin',
                type: 'string',
                filter: false
            },
            // createdDate: {
            //     title: 'Created Date / Time',
            //     type: 'date',
            //     valuePrepareFunction: (value) => { return Utils.format(value, 'dd-MMM-yyyy HH:mm:ss') },
            //     filter: false
            // }
        }
    };

    constructor(
        private pro: ProjectProvider,
        private task: TaskProvider,
        private act: ActivatedRoute,
        private acc: AccountProvider) { }

    ngOnInit() {
        this.getUserId();
        this.act.params.subscribe((params: Params) => {
            this.id = params["_id"];
            this.getProjectDetail(this.id);
            this.searchTask(1, this.id);
        });
        this.searchAcc();
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
                code: "Verify ",
                value: "Verify "
            }, {
                code: "Done",
                value: "Done"
            }]
        }

        this.lstStatus = tmpStatus.data;
    }

    public getUserId() {
        this.acc.getUserId(1).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                console.log('userId', rsp.result);
                this.userId = rsp.result;
                return;
            }
        }, (err) => { console.log(err); });
    }


    private getPager(totalItems: number, currentPage: number = 1, pageSize: number = 1) {
        let totalPages = Math.ceil(totalItems / pageSize);

        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        let startPage: number, endPage: number;
        if (totalPages <= 10) {
            // less than 10 total pages so show all
            startPage = 1;
            endPage = totalPages;
        } else {
            // more than 10 total pages so calculate start and end pages
            if (currentPage <= 6) {
                startPage = 1;
                endPage = 10;
            } else if (currentPage + 4 >= totalPages) {
                startPage = totalPages - 9;
                endPage = totalPages;
            } else {
                startPage = currentPage - 5;
                endPage = currentPage + 4;
            }
        }

        // calculate start and end item indexes
        let startIndex = (currentPage - 1) * pageSize;
        let endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

        // create an array of pages to ng-repeat in the pager control
        let pages = Array.from(Array((endPage + 1) - startPage).keys()).map(i => startPage + i);

        // return object with all pager properties required by the view
        return {
            totalItems: totalItems,
            currentPage: currentPage,
            pageSize: pageSize,
            totalPages: totalPages,
            startPage: startPage,
            endPage: endPage,
            startIndex: startIndex,
            endIndex: endIndex,
            pages: pages
        };
    }


    public setPage(page: number) {
        this.pager = this.getPager(this.total, page, this.pageSize);
        this.pagedItems = this.dataTask.slice(this.pager.startIndex, this.pager.endIndex + 1);
    }
    public searchTask(page: any, projectId: any) {
        //document.getElementById('preloader').style.display = 'block';

        let x = {
            filter: {
                project: projectId,
                status: this.taskStatus,
                asign: this.taskAssign,
                name: this.taskName
            },
            page: page,
            paging: false,
            size: this.pageSize,
            sort: [
                {
                    direction: "DESC",
                    field: ""
                }
            ],
            total: 10
        }
        console.log('xxx', x);
        
        this.task.searchTask(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.dataTask = rsp.result.data;
                console.log('hhhoan', this.dataTask);

                if (this.dataTask != null) {

                }
                this.total = rsp.result.total;
                this.setPage(page);
            }
        }, (err) => {
            console.log(err);
        });

    }

    public getProjectDetail(id: any) {
        this.pro.getProjectDetail(id).subscribe((rsp: any) => {

            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result;
                console.log('infoProject', this.data);

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
                console.log(this.lstAcc);
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

    public searchClick(page: any) {
        this.act.params.subscribe((params: Params) => {
            this.id = params["_id"];
            this.searchTask(page, this.id);
            this.curentPage = page;
        });

    }

}

