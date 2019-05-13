import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import {AccountProvider} from '../../../app/providers';
import { HTTP, Utils, Token, AccessRight, AttachmentType } from '../../../app/utilities';

@Component({
    selector: 'app-accounts',
    templateUrl: './accounts.component.html',
    styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
    @ViewChild("editAccountModal") public editAccountModal: ModalDirective;

    public show = false;
    public lstAcc = [];
    public pageSize = 10;
    public data = [];
    public total: number = 0;
    public pager: any = {};
    public pagedItems: any[];

    constructor(private pro: AccountProvider) {
    }

    ngOnInit() {
        //this.search(1);
    }

    public setPage(page: number) {
        this.pager = this.getPager(this.total, page, this.pageSize);
        this.pagedItems = this.data.slice(this.pager.startIndex, this.pager.endIndex + 1);
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

    public search(page: any) {
        document.getElementById('preloader').style.display = 'block';

        let x = {
            filter: {
            },
            page: page,
            size: this.pageSize,
            sort: [
                {
                    direction: "DESC",
                    field: "createdDate"
                }
            ]
        }

        this.pro.search(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result.data;
                console.log('dsfa',this.data);
                
                if (this.data != null) {
   
                }
                this.total = rsp.result.total;
                this.setPage(page);
            }
        }, (err) => {
            console.log(err);
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

}
