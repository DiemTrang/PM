import { Component, OnInit } from '@angular/core';
import { AccountProvider, TaskProvider, ProjectProvider } from 'src/app/providers';
import { HTTP } from '../../../app/utilities';
import { Params, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accounts-detail',
  templateUrl: './accounts-detail.component.html',
  styleUrls: ['./accounts-detail.component.css']
})
export class AccountsDetailComponent implements OnInit {
  public data: any = {};
  public id = 0;
  public show = false;
  public lstPro = [];
  public pageSize = 10;
  public dataTask = [];
  public total: number = 0;
  public pager: any = {};

  public pagedItems: any[];
  public taskName = '';
  public dataPro: any = {};
  public lstProject = [];
  public project = '';

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
        title: 'ID',
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
        title: 'Task Name',
        type: 'string',
        filter: false
      },
      status: {
        title: 'Status',
        type: 'string',
        filter: false
      },
      // role: {
      //     title: 'Role',
      //     type: 'string',
      //     filter: false
      // }
    }
  };

  constructor(
    private acc: AccountProvider,
    private task: TaskProvider,
    private act: ActivatedRoute,
    private pro: ProjectProvider, ) { }

  ngOnInit() {
    this.act.params.subscribe((params: Params) => {
      this.id = params["_id"];
      this.getAccountsDetail(this.id);
      this.searchTask(1, this.id);
    });
    this.searchProject();
  }

  public getAccountsDetail(id: any) {
    this.acc.getAccountsDetail(id).subscribe((rsp: any) => {

      if (rsp.status === HTTP.STATUS_SUCCESS) {
        this.data = rsp.result;
      } else {
        let msg = rsp.message;
      }
    });
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

  public searchProject() {
    //document.getElementById('preloader').style.display = 'block';

    let x = {
      filter: {
      },
      paging: false,
      sort: [
        {
          direction: "DESC",
          field: ""
        }
      ]
    }

    this.pro.searchProject(x).subscribe((rsp: any) => {
      let item = {
        ik: 0,
        title: "-- Please select --"
      }
      if (rsp.status === HTTP.STATUS_SUCCESS) {

        if (this.dataPro != null) {
          console.log('project List', this.dataPro);
          this.lstProject = rsp.result.data;
          this.lstProject.unshift(item);
        }
      }
      else this.lstProject.unshift(item);
    }, (err) => {
      console.log(err);
    });

  }

  public setPage(page: number) {
    this.pager = this.getPager(this.total, page, this.pageSize);
    this.pagedItems = this.dataTask.slice(this.pager.startIndex, this.pager.endIndex + 1);
  }
  public searchTask(page: any, asignId: any) {
    //document.getElementById('preloader').style.display = 'block';

    let x = {
      filter: {
        project: this.project,
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

    this.task.searchTask(x).subscribe((rsp: any) => {
      if (rsp.status === HTTP.STATUS_SUCCESS) {
        this.dataTask = rsp.result.data;
        console.log(this.dataTask);

        if (this.dataTask != null) {

        }
        this.total = rsp.result.total;
        this.setPage(page);
      }
    }, (err) => {
      console.log(err);
    });

  }

  public searchClick(page: any) {
    this.searchTask(page, null);
  }

}
