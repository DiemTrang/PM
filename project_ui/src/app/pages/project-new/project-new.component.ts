import { Component, OnInit } from '@angular/core';
import { ProjectProvider } from 'src/app/providers';;
import { HTTP } from 'src/app/utilities';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-project-new',
  templateUrl: './project-new.component.html',
  styleUrls: ['./project-new.component.css']
})
export class ProjectNewComponent implements OnInit {
  public data = [];

  constructor(
    private pro: ProjectProvider,
    private act: ActivatedRoute,
    private rou: Router) { }

  ngOnInit() {
  }

  public create() {
    document.getElementById('preloader').style.display = 'block';
    this.pro.createProject(this.data).subscribe((rsp: any) => {
        if (rsp.status === HTTP.STATUS_SUCCESS) {
            return;
        }
    }, (err) => { console.log(err); });

    setTimeout(function () {
        document.getElementById('preloader').style.display = 'none';
    }, 500);
}

}
