import { Component, OnInit, Input } from '@angular/core';
import { AccountProvider, ProjectProvider } from '../../../app/providers';
import { HTTP, Utils, Token, AccessRight, AttachmentType } from '../../../app/utilities';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  public show = false;
  public lstAcc = [];
  public pageSize = 10;
  public dataAcc = [];
  public dataPro = [];
  public total: number = 0;
  public pager: any = {};
  public pagedItems: any[];
  public data: any = {};
  public router: Router;
  public user: any = '';
  public userId = 0;

  constructor(
    private acc: AccountProvider,
    private pro: ProjectProvider,
    private act: ActivatedRoute) { }

  ngOnInit() {
    this.getUserId();
  }

  public getUserId() {
    this.acc.getUserId(1).subscribe((rsp: any) => {
      if (rsp.status === HTTP.STATUS_SUCCESS) {
        console.log('userId', rsp.result);
        this.userId = rsp.result;
        this.searchAccount(1);
        this.searchProject(1);
        this.getUserInfo(this.userId);
        return;
      }
    }, (err) => { console.log(err); });
  }

  public getUserInfo(id: number) {
    //document.getElementById('preloader').style.display = 'block';
    this.acc.read(id).subscribe((rsp: any) => {
      if (rsp.status === HTTP.STATUS_SUCCESS) {
        console.log('roleUser', rsp.result);
        
        this.data = rsp.result;
      }
      else {
        Utils.log(rsp.message);
      }
    },
      err => console.log(err));


    setTimeout(function () {
      //     document.getElementById('preloader').style.display = 'none';
    }, 500);
  }

  public setPage(page: number) {
    this.pager = this.getPager(this.total, page, this.pageSize);
    this.pagedItems = this.dataAcc.slice(this.pager.startIndex, this.pager.endIndex + 1);
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

  public searchAccount(page: any) {
    //document.getElementById('preloader').style.display = 'block';

    let x = {
      filter: {
      },
      page: page,
      size: this.pageSize,
      sort: [
        {
          direction: "DESC",
          field: ""
        }
      ]
    }

    this.acc.search(x).subscribe((rsp: any) => {
      if (rsp.status === HTTP.STATUS_SUCCESS) {
        this.dataAcc = rsp.result.data;

        if (this.dataAcc != null) {

        }
        this.total = rsp.result.total;
        this.setPage(page);
      }
    }, (err) => {
      console.log(err);
    });

    // setTimeout(function () {
    //     document.getElementById('preloader').style.display = 'none';
    // }, 500);
  }

  public setPage1(page: number) {
    this.pager = this.getPager(this.total, page, this.pageSize);
    this.pagedItems = this.dataPro.slice(this.pager.startIndex, this.pager.endIndex + 1);
  }

  public searchProject(page: any) {
    //document.getElementById('preloader').style.display = 'block';

    let x = {
      filter: {
      },
      page: page,
      size: this.pageSize,
      sort: [
        {
          direction: "DESC",
          field: ""
        }
      ]
    }

    this.pro.searchProject(x).subscribe((rsp: any) => {
      if (rsp.status === HTTP.STATUS_SUCCESS) {
        this.dataPro = rsp.result.data;

        if (this.dataPro != null) {

        }
        this.total = rsp.result.total;
        this.setPage(page);
      }
    }, (err) => {
      console.log(err);
    });

  }

}
