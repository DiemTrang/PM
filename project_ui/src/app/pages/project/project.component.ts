import { Component, OnInit } from '@angular/core';
import { TaskProvider, ProjectProvider } from 'src/app/providers';
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

  constructor(
    private pro: ProjectProvider,
    private task: TaskProvider,
    private act: ActivatedRoute,) { }

  ngOnInit() {
    this.searchTask(1);
    this.act.params.subscribe((params: Params) => {
      this.id = params["_id"];
      this.getProjectDetail(this.id);
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
  
  
  public setPage(page: number) {
    this.pager = this.getPager(this.total, page, this.pageSize);
    this.pagedItems = this.dataTask.slice(this.pager.startIndex, this.pager.endIndex + 1);
  }
  public searchTask(page: any) {
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

  public getProjectDetail(id: any) {
    this.pro.getProjectDetail(id).subscribe((rsp: any) => {
        
        if (rsp.status === HTTP.STATUS_SUCCESS) {
            this.data = rsp.result;
            
        } else {
            let msg = rsp.message;
        }
    });
}

}

